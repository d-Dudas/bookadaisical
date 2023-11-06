import { Component, Input } from '@angular/core';
import { Book } from '../classes/book';

@Component({
  selector: 'app-book-preview',
  templateUrl: './book-preview.component.html',
  styleUrls: ['./book-preview.component.css']
})
export class BookPreviewComponent {
  @Input() book: Book | undefined;
}
