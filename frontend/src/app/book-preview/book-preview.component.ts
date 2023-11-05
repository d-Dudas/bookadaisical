import { Component, Input } from '@angular/core';
import { Book } from '../book';

@Component({
  selector: 'app-book-preview',
  templateUrl: './book-preview.component.html',
  styleUrls: ['./book-preview.component.css']
})
export class BookPreviewComponent {
  // title: string | undefined;
  // author: string | undefined;
  // images: string[] | undefined;
  // purpose: BookPurpose | undefined;
  // priceCurrency: number | undefined;
  // pricePoints: number | undefined;
  // description: Text | undefined;
  @Input() book: Book | undefined;
  // constructor(private book: Book)
  // {
  //   this.title = book.title;
  //   this.author = book.author;
  //   this.images = book.images;
  //   this.purpose = book.purpose;
  //   this.priceCurrency = book.priceCurrency;
  //   this.pricePoints = book.pricePoints;
  //   this.description = book.description;
  // }
}
