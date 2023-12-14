import { Component, ElementRef, Input, ViewChild } from '@angular/core';
import { UserSlim } from '../classes/userSlim';
import { Message } from '../interfaces/message';
import { ChatService } from 'src/app/services/chat.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css'],
})
export class ChatComponent {
  @ViewChild('messageFlowContainer') private messageFlowContainer!: ElementRef;
  @Input() sender!: UserSlim;
  @Input() receiver!: UserSlim;

  private webSocket!: WebSocket;
  messages: Message[] = [];
  message: string = "";
  private messagesPerPage: number = 10;

  constructor(private chatService: ChatService) {}

  ngOnInit(){
    this.webSocket = new WebSocket('ws://localhost:8080/chat?userId=' + this.sender.id);

    this.webSocket.onmessage = (event) => {
      let data = JSON.parse(event.data);
      let response: Message = this.processMessageData(data);
      this.messages.push(response);
    }

    this.chatService.getChatHistory(this.sender.id, this.receiver.id).subscribe(
      (chats) => {
        this.messages = chats;
      },
      (error) => {
        console.error('Error fetching chat history:', error);
      }
    );
  }

  formatSentAt(dateArray: number[]): string {
    console.log(dateArray);
    const date = new Date(dateArray[0], dateArray[1] - 1, dateArray[2], dateArray[3], dateArray[4], dateArray[5], dateArray[6] / 1000000);
    return date.toISOString();
  }

  processMessageData(data: any): Message {
    console.log(data);
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
      sentAt: new Date().toISOString()
    }

    this.webSocket.send(JSON.stringify(message));
    this.messages.push(message);
    this.message = "";
  }

  isSender(message: Message): boolean{
    return message.senderId === this.sender.id;
  }

  ngAfterViewChecked() {
    this.scrollToBottom();
  }

  private scrollToBottom(): void {
    try {
      this.messageFlowContainer.nativeElement.scrollTop = this.messageFlowContainer.nativeElement.scrollHeight;
    } catch(err) { }
  }

  onScroll(): void {
    const container = this.messageFlowContainer.nativeElement;
    if (container.scrollTop === 0) {
      // Load more messages
      // const currentPage = this.messages.length / this.messagesPerPage; // Determine the current page
      // this.chatService.getChatHistory(this.sender.id, this.receiver.id, currentPage, this.messagesPerPage)
      //   .subscribe(moreMessages => {
      //     this.messages = [...moreMessages, ...this.messages];
      //   });
      console.log("On top");
    }

    container.scrolltop = container.scrollHeight;
  }

}
