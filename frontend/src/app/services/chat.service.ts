import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserChats } from '../elements/interfaces/user-chats';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private chatControllerUrl = 'http://localhost:8080/api/chat';
  private chatHistoryUrl = '/history';
  private userChatsUrl = '/chats';

  constructor(private http: HttpClient) { }

  getChatHistory(senderUsername: string, receiverUsername: string, page: number, size: number): Observable<any> {
    const params = { senderUsername: senderUsername, receiverUsername: receiverUsername, page: page.toString(), size: size.toString() };
    return this.http.get( this.chatControllerUrl + this.chatHistoryUrl, { params });
  }

  getUserChats(username: string): Observable<UserChats>
  {
    return this.http.post<UserChats>(this.chatControllerUrl + this.userChatsUrl, {username: username});
  }
}
