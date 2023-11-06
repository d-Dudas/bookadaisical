import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Store } from '@ngrx/store';
import { selectIsAuthenticated, selectUser } from 'src/app/account-management/auth.state';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-negotiate-page',
  templateUrl: './negotiate-page.component.html',
  styleUrls: ['./negotiate-page.component.css']
})
export class NegotiatePageComponent {
  bookOwnerId: number | undefined;
  clientId: number | undefined;

  constructor(private store: Store,
              private bookService: BookService,
              private route: ActivatedRoute) {}

  ngOnInit(): void
  {
    this.store.select(selectIsAuthenticated).subscribe((isAuthenticated) =>
    {
      if(isAuthenticated)
      {
        this.store.select(selectUser).subscribe((user) => {this.clientId = user?.id});
      }
      else
      {
        // TODO: force login
      }
    });

    this.route.paramMap.subscribe(params => { // TODO: handle error case
      const bookId = params.get('uniqueId');
      if(bookId !== null)
      {
        this.bookService.getBookOwnerId(parseInt(bookId, 10)).subscribe(ownerId => {
          this.bookOwnerId = ownerId;
        });
      }
    });
  }
}
