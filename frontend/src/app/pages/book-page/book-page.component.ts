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
  public visitor: UserSlim | null = null;
  public uploader: UserSlim | null = null;

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
          this.bookService.getBookByUniqueId(parseInt(bookUniqueId, 10)).subscribe(book => {
            this.book = book;

            this.accountService.getUserDetails(book.uploaderUsername).subscribe(uploader => {
              this.uploader = uploader as UserSlim;
            })
          });
        }
      });

      this.store.select(selectIsAuthenticated).subscribe((authenticated) =>
      {
        if(authenticated)
        {
          this.store.select(selectUser).subscribe((user) => { this.visitor = user; });
        }
      });
  }

  onSwapClicked(): void {
    const routePath = '/negotiate/' + this.book?.id;

    if(this.visitor !== null){
      this.router.navigate([routePath]);
    } else {
      this.store.dispatch(showAuthPopup());
      this.store.dispatch(setIntendedPath({ path: routePath }));
    }
  }

  onBuyClicked(): void {
    if(this.visitor === null) {
      this.store.dispatch(showAuthPopup());
    }
  }

  getVisitor(): UserSlim {
    if(this.visitor !== null)
    {
      return this.visitor;
    }
    let emptyUser: UserSlim = { username: ""};
    return emptyUser;
  }

  getReceiver(): UserSlim {
    if(this.uploader !== null)
    {
      return this.uploader;
    }
    let emptyUser: UserSlim = { username: ""};
    return emptyUser;
  }

  getDefaultMessage(): string {
    let defaultMessage: string = "";
    defaultMessage = "Hi! Would like to buy " + this.book?.title + ". I would like to pay in [ron/points].";

    return defaultMessage;
  }
}
