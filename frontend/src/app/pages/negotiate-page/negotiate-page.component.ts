import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Store } from '@ngrx/store';
import { selectIsAuthenticated, selectUser } from 'src/app/account-management/auth.state';
import { Book } from 'src/app/elements/classes/book';
import { BookService } from 'src/app/services/book.service';


interface negotiateItem {
  book: Book,
  selected: boolean
}
@Component({
  selector: 'app-negotiate-page',
  templateUrl: './negotiate-page.component.html',
  styleUrls: ['./negotiate-page.component.css']
})
export class NegotiatePageComponent {
  bookId: number | undefined;
  initiatorId: number | undefined;
  responderId: number | undefined;
  initiatorItems: negotiateItem[] = [];
  responderItems: negotiateItem[] = [];

  constructor(private store: Store,
              private bookService: BookService,
              private route: ActivatedRoute) {}

  ngOnInit(): void
  {
    this.store.select(selectIsAuthenticated).subscribe((isAuthenticated) =>
    {
      if(isAuthenticated)
      {
        this.store.select(selectUser).subscribe((user) => {this.initiatorId = user?.id});
        this.getInitiatorBooks();
      }
      else
      {
        // TODO: force login
      }
    });

    this.route.paramMap.subscribe(params => { // TODO: handle error case
      const bookIdString = params.get('bookId');
      if(bookIdString !== null)
      {
        this.bookId = parseInt(bookIdString, 10);
        console.log("Book id:");
        console.log(this.bookId);
        this.bookService.getBookOwnerId(this.bookId).subscribe((ownerId) => {
          console.log("Owner id:");
          console.log(ownerId);
          this.responderId = ownerId;
          this.getResponderBooks();
        });
      }
    });
  }

  getInitiatorBooks(): void {
    if(this.initiatorId === undefined) return;
    this.bookService.getUserBooks(this.initiatorId).subscribe((initiatorBooks) => {
      console.log("initiator books:");
      console.log(initiatorBooks);
      for (const book of initiatorBooks) {
        this.initiatorItems.push({book, selected: false});
      }
    })
  }

  getResponderBooks(): void {
    console.log("getResponderbooks");
    if(this.responderId === undefined) return;
    this.bookService.getUserBooks(this.responderId).subscribe((responderBooks) => {
      console.log("responder books:");
      console.log(responderBooks);
      for (const book of responderBooks) {
        this.responderItems.push({book, selected: book.uniqueId === this.bookId});
      }
    })
  }
}
