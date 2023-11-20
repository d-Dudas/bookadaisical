import { Component, OnInit } from '@angular/core';
import { Book } from '../../elements/classes/book';
import { ActivatedRoute, Router } from '@angular/router';
import { BookService } from '../../services/book.service';
import { Store } from '@ngrx/store';
import { selectIsAuthenticated } from 'src/app/account-management/auth.state';
import { setIntendedPath, showAuthPopup } from 'src/app/account-management/auth.actions';

@Component({
  selector: 'app-book-page',
  templateUrl: './book-page.component.html',
  styleUrls: ['./book-page.component.css']
})
export class BookPageComponent implements OnInit {
  book: Book | undefined;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private bookService: BookService,
    private store: Store
  ) {}

  ngOnInit(): void {
      this.route.paramMap.subscribe(params => {
        const bookUniqueId = params.get('uniqueId');
        if(bookUniqueId !== null)
        {
          this.bookService.getBookByUniqueId(parseInt(bookUniqueId, 10)).subscribe(book => {
            this.book = book;
          });
        }
      });
  }

  onSwapClicked(): void {
    let isAuthenticated;
    let routePath: string;

    this.store.select(selectIsAuthenticated).subscribe((authenticated) => {isAuthenticated = authenticated});
    routePath = '/negotiate/' + this.book?.uniqueId;

    if(isAuthenticated){
      this.router.navigate([routePath]);
    } else {
      this.store.dispatch(showAuthPopup());
      this.store.dispatch(setIntendedPath({ path: routePath }));
    }
  }
}
