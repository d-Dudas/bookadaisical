import { Component} from '@angular/core';
import { Options } from 'ngx-slider-v2';
import { Book } from 'src/app/elements/classes/book';
import { BookService } from 'src/app/services/book.service';
import { ArtisticMovement } from 'src/app/utils/enums/artistic-movement';
import { Condition } from 'src/app/utils/enums/condition';
import { Genres } from 'src/app/utils/enums/genres';
import { TargetAudience } from 'src/app/utils/enums/target-audience';

interface Filter {
  genre: string;
  targetAudience: string;
  artisticMovement: string;
  condition: string;
  contains: string;
  yearOfPublicationNotLessThen: number;
  yearOfPublicationNotBiggerThen: number;
}
@Component({
  selector: 'app-search-page',
  templateUrl: './search-page.component.html',
  styleUrls: ['./search-page.component.css']
})
export class SearchPageComponent {
  books: Book[] = [];
  genreOptions = this.getEnumValues(Genres);
  targetAudienceOptions = this.getEnumValues(TargetAudience);
  artisticMovementOptions = this.getEnumValues(ArtisticMovement);
  conditionOptions = this.getEnumValues(Condition);

  filter: Filter = {
    genre: this.genreOptions[0],
    targetAudience: this.targetAudienceOptions[0],
    artisticMovement: this.artisticMovementOptions[0],
    condition: this.conditionOptions[0],
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

  private getEnumValues(e: any) {
    return Object.keys(e).filter(key => isNaN(Number(key)));
  }

  filterChanged()
  {
    this.updateBookList();
  }

  updateBookList()
  {
    this.bookService.getFilteredBooks(this.filter).subscribe((books) => {
      this.books = books;
    });
  }
}
