import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private chatHistoryUrl = 'http://localhost:8080/chat/history';

  constructor(private http: HttpClient) { }

  getChatHistory(firstUsername: string, secondUsername: string, page: number, size: number): Observable<any> {
    const params = { firstUsername: firstUsername, secondUsername: secondUsername, page: page.toString(), size: size.toString() };
    return this.http.get(this.chatHistoryUrl, { params });
  }
}
