import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private chatHistoryUrl = 'http://localhost:8080/chat/history';

  constructor(private http: HttpClient) { }

  getChatHistory(senderUsername: string, receiverUsername: string, page: number, size: number): Observable<any> {
    const params = { senderUsername: senderUsername, receiverUsername: receiverUsername, page: page.toString(), size: size.toString() };
    return this.http.get(this.chatHistoryUrl, { params });
  }
}
