import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ArtisticMovement } from 'src/app/elements/enums/artistic-movement';
import { Condition } from 'src/app/elements/enums/condition';
import { Genres } from 'src/app/elements/enums/genres';
import { TargetAudience } from 'src/app/elements/enums/target-audience';
import { TradingOption } from 'src/app/elements/enums/trading-option';
import { getEnumValues } from 'src/app/utils/get-enum-values';
import { Book } from 'src/app/elements/classes/book';
import { Store } from '@ngrx/store';
import { selectIsAuthenticated, selectUser } from 'src/app/account-management/auth.state';
import { Router } from '@angular/router';
import { BookService } from 'src/app/services/book.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-add-book-page',
  templateUrl: './add-book-page.component.html',
  styleUrl: './add-book-page.component.css'
})
export class AddBookPageComponent {
  years: number[] = [];
  artisticMovements: string[] = getEnumValues(ArtisticMovement);
  targetAudiences: string[] = getEnumValues(TargetAudience);
  genres: string[] = getEnumValues(Genres);
  tradingOptions: string[] = getEnumValues(TradingOption);
  conditions: string[] = getEnumValues(Condition);
  imageUrls: string[] = [];
  uploaderUsername: string = '';

  bookDescription = this._formBuilder.group({
    title: ['', [Validators.required, Validators.maxLength(100)]],
    author: ['', [Validators.required, Validators.maxLength(100)]],
    description: ['', Validators.maxLength(1000)],
  });

  bookDetails = this._formBuilder.group({
    genres: ['' || [''], Validators.required],
    yearOfPublication: ['', [Validators.required, Validators.min(0)]],
    artisticMovement: ['', Validators.required],
    targetAudience: ['', Validators.required],
    bookCondition: ['', Validators.required],
    tradingOption: ['' || [''], Validators.required],
  });

  prices = this._formBuilder.group({
    priceInCurrency: ['', Validators.min(1)],
    priceInPoints: ['', Validators.min(1)],
  });

  images = this._formBuilder.group({
    list: [[''], Validators.required]
  });

  constructor(private _formBuilder: FormBuilder,
              private store: Store,
              private router: Router,
              private bookService: BookService,
              private snackBar: MatSnackBar) {
    this.artisticMovements.shift();
    this.targetAudiences.shift();
    this.genres.shift();
    this.conditions.shift();
    this.tradingOptions.shift();
    this.images.patchValue({list: undefined});
  }

  ngOnInit()
  {
    this.store.select(selectIsAuthenticated).subscribe(isAuthenticated => {
      if(isAuthenticated)
      {
        this.store.select(selectUser).subscribe(username => {
          this.uploaderUsername = username!;
        });
      } else {
        this.router.navigate(['/home']);
      }
    });

    const currentYear = new Date().getFullYear();
    for (let year = currentYear; year >= 1800; year--) {
      this.years.push(year);
    }
  }

  isPriceNecessary()
  {
    return this.bookDetails.getRawValue().tradingOption?.includes(TradingOption.ALL) ||
            this.bookDetails.getRawValue().tradingOption?.includes(TradingOption.CURRENCY) ||
            this.bookDetails.getRawValue().tradingOption?.includes(TradingOption.POINTS);
  }

  isPriceInCurrency()
  {
    return this.bookDetails.getRawValue().tradingOption?.includes(TradingOption.ALL) ||
            this.bookDetails.getRawValue().tradingOption?.includes(TradingOption.CURRENCY);
  }

  isPriceInPoints()
  {
    return this.bookDetails.getRawValue().tradingOption?.includes(TradingOption.ALL) ||
            this.bookDetails.getRawValue().tradingOption?.includes(TradingOption.POINTS);
  }

  onFilesSelected(event: any): void {
    const files = event.target.files;

    for (let file of files) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.imageUrls.push(e.target.result);
        this.images.patchValue({list: this.imageUrls});
      };
      reader.readAsDataURL(file);
    }
  }

  removeImage(index: number): void {
    this.imageUrls.splice(index, 1);
    this.images.patchValue({list: this.imageUrls.length > 0 ? this.imageUrls : undefined});
  }

  private extractBase64Data(base64String: string): string {
    const commaIndex = base64String.indexOf(',');
    if (commaIndex !== -1 && commaIndex + 1 < base64String.length) {
      return base64String.substring(commaIndex + 1);
    }

    return base64String;
  }

  confirmUpload()
  {
    let book = {
      title: this.bookDescription.getRawValue().title!,
      author: this.bookDescription.getRawValue().author!,
      description: this.bookDescription.getRawValue().description!,
      yearOfPublication: +this.bookDetails.getRawValue().yearOfPublication!,
      artisticMovement: this.bookDetails.getRawValue().artisticMovement!.toUpperCase(),
      targetAudience: this.bookDetails.getRawValue().targetAudience!.toUpperCase(),
      condition: this.bookDetails.getRawValue().bookCondition!.toUpperCase(),
      images: this.imageUrls.map(imageUrl => this.extractBase64Data(imageUrl)),
      genres: this.bookDetails.getRawValue().genres?.map(genre => genre.toUpperCase()),
      tradingOptions: this.bookDetails.getRawValue().tradingOption?.map(to => to.toUpperCase()),
      priceCurrency: this.prices.getRawValue().priceInCurrency,
      pricePoints: this.prices.getRawValue().priceInPoints,

      uploaderUsername: this.uploaderUsername,
    };

    this.bookService.uploadNewBook(book).subscribe({
      next: response => {
        this.router.navigate(['/home'])
      },
      error: () => {
        this.snackBar.open("Something went wrong. Please try again later.", undefined, {
          duration: 5 * 1000,
        });
      }
    });
  }
}
