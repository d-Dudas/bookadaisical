import { Component} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from 'src/app/elements/classes/book';
import { Filter } from 'src/app/elements/interfaces/filter';
import { BookService } from 'src/app/services/book.service';
import { ArtisticMovement } from 'src/app/elements/enums/artistic-movement';
import { Condition } from 'src/app/elements/enums/condition';
import { Genres } from 'src/app/elements/enums/genres';
import { TargetAudience } from 'src/app/elements/enums/target-audience';

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

  constructor(private bookService: BookService,
              private activatedRoute: ActivatedRoute,
              private router: Router) {
    this.activatedRoute.queryParams.subscribe(params => {
        this.filter.genre = params['genre'] || this.genreOptions[0];
    });
  }

  ngOnInit(): void {
    this.updateBookList();
  }

  private getEnumValues(e: any) {
    return Object.keys(e).filter(key => isNaN(Number(key)));
  }

  updateBookList()
  {
    this.bookService.getFilteredBooks(this.filter).subscribe((books) => {
      this.books = books;
    });

    this.router.navigate([], {
      relativeTo: this.activatedRoute,
      queryParams: this.filter,
      queryParamsHandling: 'merge',
    });
  }
}
