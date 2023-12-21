import { Component, OnInit } from '@angular/core';
import { Book } from '../../elements/classes/book';
import { ActivatedRoute, Router } from '@angular/router';
import { BookService } from '../../services/book.service';
import { AccountService} from '../../services/account.service';
import { Store } from '@ngrx/store';
import { selectIsAuthenticated, selectUser } from 'src/app/account-management/auth.state';
import { setIntendedPath, showAuthPopup } from 'src/app/account-management/auth.actions';
import { UserSlim } from 'src/app/elements/classes/userSlim';

@Component({
  selector: 'app-book-page',
  templateUrl: './book-page.component.html',
  styleUrls: ['./book-page.component.css']
})
export class BookPageComponent implements OnInit {
  public book: Book | undefined;
  public visitorUsername: string | null = null;
  public uploaderUsername: string | null = null;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private bookService: BookService,
    private accountService: AccountService,
    private store: Store
  ) {}

  ngOnInit(): void {
      this.route.paramMap.subscribe(params => {
        const bookUniqueId = params.get('uniqueId');
        if(bookUniqueId !== null)
        {
          this.bookService.getBookByUniqueId(bookUniqueId).subscribe(book => {
            console.log(book);
            this.book = book;
            console.log(this.book);

            this.accountService.getUserDetails(book.uploaderUsername).subscribe(uploader => {
              this.uploaderUsername = uploader.username;
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
  }

  onSwapClicked(): void {
    const routePath = '/negotiate/' + this.book?.id;

    if(this.visitorUsername !== null){
      this.router.navigate([routePath]);
    } else {
      this.store.dispatch(showAuthPopup());
      this.store.dispatch(setIntendedPath({ path: routePath }));
    }
  }

  onBuyClicked(): void {
    if(this.visitorUsername === null) {
      this.store.dispatch(showAuthPopup());
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
}
