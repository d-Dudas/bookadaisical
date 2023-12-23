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

  responsiveOptions: any[] | undefined;

  constructor(private bookService: BookService)
  {
  }

  ngOnInit(): void {
    this.bookService.getTopTenBooks().subscribe((books) => {
      this.books = books;
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
}
