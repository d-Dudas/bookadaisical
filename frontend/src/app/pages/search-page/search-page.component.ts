import { Component} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from 'src/app/elements/classes/book';
import { Filter } from 'src/app/elements/interfaces/filter';
import { BookService } from 'src/app/services/book.service';
import { ArtisticMovement } from 'src/app/elements/enums/artistic-movement';
import { Condition } from 'src/app/elements/enums/condition';
import { Genres } from 'src/app/elements/enums/genres';
import { TargetAudience } from 'src/app/elements/enums/target-audience';
import { FormBuilder, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-search-page',
  templateUrl: './search-page.component.html',
  styleUrls: ['./search-page.component.css']
})
export class SearchPageComponent {
  books: Book[] = [];
  genreOptions = this.getEnumValues(Genres).map(option => ({ label: this.formatGenre(option), value: option }));
  targetAudienceOptions = this.getEnumValues(TargetAudience);
  artisticMovementOptions = this.getEnumValues(ArtisticMovement);
  conditionOptions = this.getEnumValues(Condition);
  authors: string[] = [];

  filter: Filter = {
    genre: [this.genreOptions[0].value],
    targetAudience: this.targetAudienceOptions[0],
    artisticMovement: this.artisticMovementOptions[0],
    condition: this.conditionOptions[0],
    yearOfPublicationNotLessThen: 1800, // TODO: Dynamically get the "oldest" book from database
    yearOfPublicationNotBiggerThen: 2024, // TODO: Dynamically read maximum year
    contains: "",
    author: ""
  }

  filterForm = this.formBuilder.group({
    genre: [['']],
    targetAudience: [''],
    artisticMovement: [''],
    condition: [''],
    yearOfPublicationNotLessThen: [1800],
    yearOfPublicationNotBiggerThen: [2024],
    contains: [''],
    author: ['']
  });

  constructor(private bookService: BookService,
              private activatedRoute: ActivatedRoute,
              private formBuilder: FormBuilder,
              private router: Router) 
  {
  }

  ngOnInit(): void {
    console.log('x');
    this.bookService.getAuthors().subscribe({
      next: authorsDto => {
        console.log(authorsDto);
        this.authors = authorsDto.authors;
      },
      error: (error) => {
        console.log(error);
      }
    })
    this.activatedRoute.queryParams.subscribe(params => {
        const genreValue = params['genre'] ? (Array.isArray(params['genre']) ? params['genre'] : [params['genre']]) : [this.genreOptions[0].value];
        this.filterForm.controls.genre.setValue(genreValue);
        this.filterForm.controls.targetAudience.setValue(params['targetAudience'] || this.targetAudienceOptions[0]);
        this.filterForm.controls.artisticMovement.setValue(params['artisticMovement'] || this.artisticMovementOptions[0]);
        this.filterForm.controls.condition.setValue(params['condition'] || this.conditionOptions[0]);
    });

    this.updateBookList();
  }

  private getEnumValues(e: any) {
    return Object.keys(e).filter(key => isNaN(Number(key)));
  }


  formatGenre(genre: string): string {
    return (genre.charAt(0) + genre.slice(1).toLowerCase()).split('_').join(' ');
  }

  public updateBookList()
  {
    this.filter = this.filterForm.getRawValue() as Filter;
    console.log(this.filter);
    this.bookService.getFilteredBooks(this.filter).subscribe((books) => {
      this.books = books;
    });

    this.router.navigate([], {
      relativeTo: this.activatedRoute,
      queryParams: this.filterForm.value, //aici in loc the value era getRawValue()
    });
  }

}
