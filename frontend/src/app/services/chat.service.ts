import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Message } from '../elements/interfaces/message';
// import { MessageDto } from './message-dto'; // Adjust the path as needed

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  private chatHistoryUrl = 'http://localhost:8080/chat/history'; // Adjust as needed

  constructor(private http: HttpClient) { }

  getChatHistory(user1Id: number, user2Id: number): Observable<Message[]> {
    const params = { user1Id: user1Id.toString(), user2Id: user2Id.toString() };
    return this.http.get<Message[]>(this.chatHistoryUrl, { params });
  }
}