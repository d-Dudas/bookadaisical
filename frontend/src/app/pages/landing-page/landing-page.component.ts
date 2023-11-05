import { Component } from '@angular/core';
import { BookService } from '../../services/book.service';
import { Book } from '../../elements/classes/book';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})
export class LandingPageComponent {
  books: Book[] = [];

  constructor(private bookService: BookService)
  {
  }

  ngOnInit(): void {
    this.bookService.getTopTenBooks().subscribe((books) => {
      this.books = books;
    });
  }
}
