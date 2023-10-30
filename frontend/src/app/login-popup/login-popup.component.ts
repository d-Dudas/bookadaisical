import { Component } from '@angular/core';

@Component({
  selector: 'app-login-popup',
  templateUrl: './login-popup.component.html',
  styleUrls: ['./login-popup.component.css']
})
export class LoginPopupComponent {
  isPopupVisible: boolean = false;
  popupButtonText: string = "Show popup";

  showPopup(event: MouseEvent)
  {
    if(event.target === event.currentTarget)
    {
      this.isPopupVisible = !this.isPopupVisible;
      if (this.isPopupVisible)
      {
        this.popupButtonText = "Hide popup";
      }
      else
      {
        this.popupButtonText = "Show popup";
      }
    }
  }
}
