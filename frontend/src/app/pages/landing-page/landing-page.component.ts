import { Component } from '@angular/core';
import { BookService } from '../../services/book.service';
import { Book } from '../../elements/classes/book';
import { PopularGenre } from 'src/app/elements/interfaces/popular-genre';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})
export class LandingPageComponent {
  topBooks: Book[] = [];
  recentlyAddedBooks: Book[] = [];
  mostPopularCategories: PopularGenre[] = []

  responsiveOptions: any[] | undefined;

  constructor(private bookService: BookService)
  {
  }

  ngOnInit(): void {
    this.bookService.getTopTenBooks().subscribe((books) => {
      this.topBooks = books;
    });

    this.bookService.getRecentlyAddedBooks().subscribe((books) => {
      this.recentlyAddedBooks = books;
    });

    this.bookService.getMostPopularCategories().subscribe((categories) => {
      this.mostPopularCategories = categories;
      console.log(this.mostPopularCategories);
    })

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
