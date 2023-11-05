import { Component} from '@angular/core';
import { Book } from '../book';
import { WritingGenres } from '../writing-genres';
import { TargetAudience } from '../target-audience';
import { ArtisticMovement } from '../artistic-movement';
import { Condition } from '../condition';
import { Options } from 'ngx-slider-v2';
import { BookService } from '../book.service';

@Component({
  selector: 'app-search-page',
  templateUrl: './search-page.component.html',
  styleUrls: ['./search-page.component.css']
})
export class SearchPageComponent {
  books: Book[] = [];
  genreOptions = Object.values(WritingGenres);
  targetAudienceOptions = Object.values(TargetAudience);
  artisticMovementOptions = Object.values(ArtisticMovement);
  conditionOptions = Object.values(Condition);

  filters = {
    genre: "",
    targetAudience: "",
    artistitMovement: "",
    condition: "",
    yearOfPublicationNotLessThen: 1950, // TODO: Dynamically get the "oldest" book from database
    yearOfPublicationNotBiggerThen: 2023, // TODO: Dynamically read maximum year
    contains: ""
  }

  value: number = 100;
  highValue: number = 150;
  options: Options = {
    floor:1950,
    ceil: 2023
  };

  constructor(private bookService: BookService)
  {
  }

  ngOnInit(): void {
    this.updateBookList();
  }

  genreChanged()
  {
    this.updateBookList();
  }

  updateBookList()
  {
    this.bookService.getFilteredBooks(this.filters).subscribe((books) => {
      this.books = books;
    });
  }
}
