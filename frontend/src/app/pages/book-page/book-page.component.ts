import { Component, OnInit } from '@angular/core';
import { Book } from '../../elements/classes/book';
import { ActivatedRoute, Router } from '@angular/router';
import { BookService } from '../../services/book.service';
import { Store } from '@ngrx/store';
import { selectIsAuthenticated } from 'src/app/account-management/auth.state';
import { showAuthPopup } from 'src/app/account-management/auth.actions';

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
    console.log(this.store.select(selectIsAuthenticated).subscribe((authenticated) => {return authenticated}));
    let isAuthenticated;
    this.store.select(selectIsAuthenticated).subscribe((authenticated) => {isAuthenticated = authenticated});
    if(isAuthenticated){
      this.router.navigate(['/negotiate/' + this.book?.uniqueId]);
    } else {
      this.store.dispatch(showAuthPopup());
    }
  }
}
