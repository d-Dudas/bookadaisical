import { Component, ElementRef, Input, ViewChild } from '@angular/core';
import { UserSlim } from '../classes/userSlim';

interface Message {
  senderId: number;
  receiverId: number;
  message: string;
  sentAt: string;
}

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

  constructor() {}

  ngOnInit(){
    console.log(this.sender);
    console.log(this.receiver);
    this.webSocket = new WebSocket('ws://localhost:8080/chat?userId='+this.sender.id);
    this.webSocket.onmessage = (event) => {
      let data = JSON.parse(event.data);
      console.log(data);
      let response: Message = this.processMessageData(data);
      console.log(response);
      this.messages.push(response);
    }
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
    console.log(message);
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

}
