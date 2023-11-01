import { Component} from '@angular/core';
import { AuthService } from '../account-management/auth-service.service';

@Component({
  selector: 'app-authenticate-popup',
  templateUrl: './authenticate-popup.component.html',
  styleUrls: ['./authenticate-popup.component.css']
})
export class AuthenticatePopupComponent {
  isButtonVisible: boolean = true;
  isPopupVisible: boolean = false;
  isLoginPopupVisible: boolean = false;
  isRegisterPopupVisible: boolean = false;
  popupButtonText: string = this.isPopupVisible ? "Hide popup" : "Show popup";

  constructor(private authService: AuthService) {}

  showPopup(event: MouseEvent)
  {
    if(event.target === event.currentTarget)
    {
      this.isPopupVisible = !this.isPopupVisible;
      this.isLoginPopupVisible = this.isPopupVisible;
      this.isRegisterPopupVisible = false;
    }
  }

  signOut()
  {
    this.authService.logout();
    this.isButtonVisible = true;
  }

  authenticateDone()
  {
    this.isLoginPopupVisible = false;
    this.isPopupVisible = false;
    this.isPopupVisible = false;
    this.isButtonVisible = false;
  }

  showRegisterPopup()
  {
    this.isLoginPopupVisible = false;
    this.isRegisterPopupVisible = true;
  }

  showLoginPopup()
  {
    this.isRegisterPopupVisible = false;
    this.isLoginPopupVisible = true;
  }
}
