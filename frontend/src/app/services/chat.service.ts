import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private chatHistoryUrl = 'http://localhost:8080/chat/history';

  constructor(private http: HttpClient) { }

  getChatHistory(user1Id: number, user2Id: number, page: number, size: number): Observable<any> {
    const params = { user1Id: user1Id.toString(), user2Id: user2Id.toString(), page: page.toString(), size: size.toString() };
    return this.http.get(this.chatHistoryUrl, { params });
  }
}
