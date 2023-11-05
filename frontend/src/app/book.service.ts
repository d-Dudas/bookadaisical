import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Book } from './book';

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private backendUrl = 'http://localhost:8080';
  private getPopularBooksUrl = '/top-ten-books';
  private filteredSeacrchUrl = '/filtered-search'

  constructor(private http: HttpClient)
  {
  }

  getTopTenBooks(): Observable<Book[]>
  {
    return this.http.get<Book[]>(this.backendUrl + this.getPopularBooksUrl);
  }

  getFilteredBooks(filters: {}): Observable<Book[]>
  {
    return this.http.post<Book[]>(this.backendUrl + this.filteredSeacrchUrl, filters);
  }
}
