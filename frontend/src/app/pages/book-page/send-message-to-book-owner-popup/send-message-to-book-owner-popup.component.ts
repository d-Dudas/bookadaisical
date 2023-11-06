import { Component } from '@angular/core';

@Component({
  selector: 'app-send-message-to-book-owner-popup',
  templateUrl: './send-message-to-book-owner-popup.component.html',
  styleUrls: ['./send-message-to-book-owner-popup.component.css']
})
export class SendMessageToBookOwnerPopupComponent {
  isBuyPopupVisible: boolean = false;
  messageToOwner: string = "Hi! I would like to buy this book. I would like to pay in [ron/points].";


  showBuyBookPopup(): void
  {
    this.isBuyPopupVisible = true;
  }

  hideBuyBookPopup(event: MouseEvent)
  {
    if(event.target === event.currentTarget)
    {
      this.isBuyPopupVisible = false;
    }
  }

  isMessageEmpty(): boolean
  {
    return this.messageToOwner.length === 0;
  }

  sendMessage(): void
  {
    console.log("Sending messaage:");
    console.log(this.messageToOwner);
  }
}
