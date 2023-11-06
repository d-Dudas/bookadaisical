import { Component, OnInit } from '@angular/core';
import { Book } from '../../elements/classes/book';
import { ActivatedRoute } from '@angular/router';
import { BookService } from '../../services/book.service';

@Component({
  selector: 'app-book-page',
  templateUrl: './book-page.component.html',
  styleUrls: ['./book-page.component.css']
})
export class BookPageComponent implements OnInit {
  book: Book | undefined;

  constructor(
    private route: ActivatedRoute,
    private bookService: BookService
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
}
