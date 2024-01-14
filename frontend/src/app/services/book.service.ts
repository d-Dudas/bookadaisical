import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Book } from '../elements/classes/book';
import { UserSlim } from '../elements/classes/userSlim';
import { Genres } from '../elements/enums/genres';
import { PopularGenre } from '../elements/interfaces/popular-genre';

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private backendUrl = 'http://localhost:8080';
  private getPopularBooksUrl = '/top-ten-books';
  private getFilteredBooksUrl = '/get-filtered-books';
  private getBookByIdUrl = '/get-book-by-id';
  private getBookOwnerIdUrl = '/get-book-owner-username';
  private getUserBooksUrl = '/get-user-books';
  private getRecentlyAddedBooksUrl = '/books/recently-added-books';
  private getMostPopularCategoriesUrl = '/books/most-popular-categories';
  private uploadNewBookUrl = '/books/upload-new';

  constructor(private http: HttpClient)
  {
  }

  getTopTenBooks(): Observable<Book[]>
  {
    return this.http.get<Book[]>(this.backendUrl + this.getPopularBooksUrl);
  }

  getRecentlyAddedBooks(): Observable<Book[]>
  {
    return this.http.get<Book[]>(this.backendUrl + this.getRecentlyAddedBooksUrl);
  }

  getMostPopularCategories(): Observable<PopularGenre[]>
  {
    return this.http.get<PopularGenre[]>(this.backendUrl + this.getMostPopularCategoriesUrl);
  }

  getFilteredBooks(filters: {}): Observable<Book[]>
  {
    return this.http.post<Book[]>(this.backendUrl + this.getFilteredBooksUrl, filters);
  }

  getBookByUniqueId(uniqueId: string)
  {
    return this.http.post<Book>(this.backendUrl + this.getBookByIdUrl, uniqueId);
  }

  getBookOwnerUsername(bookId: string): Observable<UserSlim>
  {
    return this.http.get<UserSlim>(this.backendUrl + this.getBookOwnerIdUrl + "/" + bookId.toString());
  }

  getUserBooks(username: string): Observable<Book[]>
  {
    return this.http.get<Book[]>(this.backendUrl + this.getUserBooksUrl + "/" + username);
  }

  uploadNewBook(book: any)
  {
    return this.http.post(this.backendUrl + this.uploadNewBookUrl, book);
  }
}
