import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Book } from '../elements/classes/book';

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private backendUrl = 'http://localhost:8080';
  private getPopularBooksUrl = '/top-ten-books';
  private getFilteredBooksUrl = '/get-filtered-books';
  private getBookByIdUrl = '/get-book';
  private getBookOwnerIdUrl = '/get-book-owner-id';

  constructor(private http: HttpClient)
  {
  }

  getTopTenBooks(): Observable<Book[]>
  {
    return this.http.get<Book[]>(this.backendUrl + this.getPopularBooksUrl);
  }

  getFilteredBooks(filters: {}): Observable<Book[]>
  {
    return this.http.post<Book[]>(this.backendUrl + this.getFilteredBooksUrl, filters);
  }

  getBookByUniqueId(uniqueId: number)
  {
    return this.http.post<Book>(this.backendUrl + this.getBookByIdUrl, uniqueId);
  }

  getBookOwnerId(bookId: number): Observable<number>
  {
    return this.http.post<number>(this.backendUrl + this.getBookOwnerId, bookId);
  }
}
