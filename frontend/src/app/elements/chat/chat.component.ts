import { Component, ElementRef, Input, ViewChild } from '@angular/core';
import { UserSlim } from '../classes/userSlim';
import { Message } from '../interfaces/message';
import { ChatService } from 'src/app/services/chat.service';
import { elementAt } from 'rxjs';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css'],
})
export class ChatComponent {
  @ViewChild('messageFlowContainer') private messageFlowContainer!: ElementRef;
  @Input() sender!: UserSlim;
  @Input() receiver!: UserSlim;
  @Input() defaultMessage: string = "";

  private webSocket!: WebSocket;
  messages: Message[] = [];
  message: string = "";
  private messagesPerPage: number = 15;
  private messagePage = 0;

  constructor(private chatService: ChatService) {}

  ngOnInit(){
    this.message = this.defaultMessage;
    this.webSocket = new WebSocket('ws://localhost:8080/chat?userId=' + this.sender.id);

    this.webSocket.onmessage = (event) => {
      let data = JSON.parse(event.data);
      let response: Message = this.processMessageData(data);
      this.messages = [response, ...this.messages];
      this.scrollToBottom();
    }

    this.chatService.getChatHistory(this.sender.id, this.receiver.id, this.messagePage, this.messagesPerPage).subscribe(
      (chats) => {
        this.messages = chats.content;
      },
      (error) => {
        console.error('Error fetching chat history:', error);
      }
    );
  }

  formatSentAt(dateArray: number[]): string {
    const date = new Date(dateArray[0], dateArray[1] - 1, dateArray[2], dateArray[3], dateArray[4], dateArray[5], dateArray[6] / 1000000);
    return date.toISOString();
  }

  processMessageData(data: any): Message {
    return {
      senderId: data.senderId,
      receiverId: data.receiverId,
      message: data.message,
      sentAt: this.formatSentAt(data.sentAt),
    };
  }

  sendMessage(): void{
    if(this.message === "") return;

    let message: Message = {
      senderId: this.sender.id,
      receiverId: this.receiver.id,
      message: this.message,
      sentAt: this.getLocalIsoTimeString()
    }

    this.webSocket.send(JSON.stringify(message));
    this.messages = [message, ...this.messages];
    this.message = "";
  }

  private getLocalIsoTimeString(): string {
    const now = new Date();
    const timezoneOffsetInMs = now.getTimezoneOffset() * 60000;
    const localTime = new Date(now.getTime() - timezoneOffsetInMs);

    const pad = (num: any) => String(num).padStart(2, '0');

    // Format: YYYY-MM-DDTHH:mm:ss.sss
    return localTime.getFullYear() + '-' +
           pad(localTime.getMonth() + 1) + '-' +
           pad(localTime.getDate()) + 'T' +
           pad(localTime.getHours()) + ':' +
           pad(localTime.getMinutes()) + ':' +
           pad(localTime.getSeconds()) + '.' +
           String(localTime.getMilliseconds()).padStart(3, '0');
  }

  isSender(message: Message): boolean{
    return message.senderId === this.sender.id;
  }

  private scrollToBottom(): void {
    try {
      this.messageFlowContainer.nativeElement.scrollTop = this.messageFlowContainer.nativeElement.scrollHeight;
    } catch(err) { }
  }

  onScroll(): void {
    const container = this.messageFlowContainer.nativeElement;
    const atTop = container.scrollHeight + container.scrollTop === container.clientHeight;

    if (atTop) {
      this.messagePage += 1;
      this.chatService.getChatHistory(this.sender.id, this.receiver.id, this.messagePage, this.messagesPerPage)
        .subscribe(moreMessages => {
          this.messages = [...this.messages, ...moreMessages.content];
        });
    }
  }
}
