import { Component} from '@angular/core';
import { Options } from 'ngx-slider-v2';
import { Book } from 'src/app/elements/classes/book';
import { BookService } from 'src/app/services/book.service';
import { ArtisticMovement } from 'src/app/utils/enums/artistic-movement';
import { Condition } from 'src/app/utils/enums/condition';
import { TargetAudience } from 'src/app/utils/enums/target-audience';
import { WritingGenres } from 'src/app/utils/enums/writing-genres';

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
