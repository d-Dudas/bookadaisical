import { Component, OnInit, ViewChild } from '@angular/core';
import { Book } from '../../elements/classes/book';
import { ActivatedRoute, Router } from '@angular/router';
import { BookService } from '../../services/book.service';
import { AccountService} from '../../services/account.service';
import { Store } from '@ngrx/store';
import { selectIsAuthenticated, selectUser } from 'src/app/account-management/auth.state';
import { setIntendedPath, showAuthPopup } from 'src/app/account-management/auth.actions';
import { NegotiationService } from 'src/app/services/negotiation.service';
import { DatePipe } from '@angular/common';
import { MatDrawer } from '@angular/material/sidenav';
import { TradingOption } from 'src/app/elements/enums/trading-option';

@Component({
  selector: 'app-book-page',
  templateUrl: './book-page.component.html',
  styleUrls: ['./book-page.component.css']
})
export class BookPageComponent implements OnInit {
  public book: Book | undefined;
  public visitorUsername: string | null = null;
  public uploaderUsername: string | null = null;
  public bookImageIndex: number = 0;
  @ViewChild('drawer') drawer!: MatDrawer;
  public otherBooks: Book[] = [];
  public responsiveOptions: any[] | undefined;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private bookService: BookService,
    private accountService: AccountService,
    private store: Store,
    private negotiationService: NegotiationService,
    private datePipe: DatePipe
  ) {}

  ngOnInit(): void {
      this.route.paramMap.subscribe(params => {
        const bookUniqueId = params.get('uniqueId');
        if(bookUniqueId !== null)
        {
          this.bookService.getBookByUniqueId(bookUniqueId).subscribe(book => {
            this.book = book;
            this.bookService.updateView({id: this.book?.id!}).subscribe({
              error: (error) => {
                console.log(error);
              }
            })
            this.accountService.getUserDetails(book.uploaderUsername).subscribe(uploader => {
              this.uploaderUsername = uploader.username;

              this.bookService.getRecommendedBooks({username: this.uploaderUsername!}).subscribe({
                next: books => {
                  this.otherBooks = books;
                },
                error: (error) => {
                  console.log(error);
                }
              });
            })
          });
        }
      });

      this.store.select(selectIsAuthenticated).subscribe((authenticated) =>
      {
        if(authenticated)
        {
          this.store.select(selectUser).subscribe((username) => { this.visitorUsername = username; });
        }
      });

      this.responsiveOptions = [
        {
            breakpoint: '1199px',
            numVisible: 1,
            numScroll: 1
        },
        {
            breakpoint: '991px',
            numVisible: 2,
            numScroll: 1
        },
        {
            breakpoint: '767px',
            numVisible: 1,
            numScroll: 1
        }
      ];
  }

  onSwapClicked(): void {
    const routePath = '/negotiate';
    this.negotiationService.setStoredBookId(this.book?.id!);
    this.negotiationService.setResponderUsername(this.book?.uploaderUsername!);

    if(this.visitorUsername !== null){
      this.router.navigate([routePath]);
    } else {
      this.store.dispatch(showAuthPopup());
      this.store.dispatch(setIntendedPath({ path: routePath }));
    }
  }

  public getGenreString(): string
  {
    return this.book?.genres.map(genre => this.getEnumValueAsString(genre) ).join(',')!;
  }

  public getTradingOptionsString(): string
  {
    return this.book?.tradingOptions.map(genre => this.getEnumValueAsString(genre) ).join(',')!;
  }

  onBuyClicked(): void {
    if(this.visitorUsername === null) {
      this.store.dispatch(showAuthPopup());
    } else {
      this.drawer.toggle();
    }
  }

  getVisitorUsername(): string {
    if(this.visitorUsername !== null)
    {
      return this.visitorUsername;
    }
    return "";
  }

  getReceiverUsername(): string {
    if(this.uploaderUsername !== null)
    {
      return this.uploaderUsername;
    }
    return "";
  }

  getDefaultMessage(): string {
    let defaultMessage: string = "";
    defaultMessage = "Hi! Would like to buy " + this.book?.title + ". I would like to pay in [ron/points].";

    return defaultMessage;
  }

  nextBookImage(): void
  {
    this.bookImageIndex++;
    this.bookImageIndex %= this.book?.images?.length!;
  }

  previousBookImage(): void
  {
    this.bookImageIndex > 0 ?
      this.bookImageIndex-- :
      this.bookImageIndex = this.book?.images?.length!-1;
  }

  public getEnumValueAsString(value: any): string
  {
    if(value === null) return '';
    return (value.charAt(0) + value.slice(1).toLowerCase()).split('_').join(' ');
  }

  public getDateString()
  {
    return this.datePipe.transform(this.book?.createdOn, 'MMMM d, y');
  }

  doesBookHavePriceCurrency(): boolean
  {
    return this.book?.tradingOptions.includes(TradingOption.ALL)! ||
          this.book?.tradingOptions.includes(TradingOption.CURRENCY)!;
  }

  doesBookHavePricePoints(): boolean
  {
    return this.book?.tradingOptions.includes(TradingOption.ALL)! ||
          this.book?.tradingOptions.includes(TradingOption.POINTS)!;
  }

  getTradingOptions()
  {
    let tradingOptions = [];
    if(this.book?.tradingOptions.includes(TradingOption.ALL))
    {
      tradingOptions.push(TradingOption.CURRENCY);
      tradingOptions.push(TradingOption.POINTS);
      tradingOptions.push(TradingOption.SWAP);
    } else {
      tradingOptions = this.book?.tradingOptions!;
    }

    return tradingOptions;
  }

  isForBuy()
  {
    return this.book?.tradingOptions.includes(TradingOption.ALL) || this.book?.tradingOptions.includes(TradingOption.CURRENCY) || this.book?.tradingOptions.includes(TradingOption.POINTS);
  }

  isForSwap()
  {
    return this.book?.tradingOptions.includes(TradingOption.ALL) || this.book?.tradingOptions.includes(TradingOption.SWAP);
  }
}
