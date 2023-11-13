import { Component} from '@angular/core';
import { AuthService } from '../../../account-management/auth-service.service';
import { Store } from '@ngrx/store';
import { selectIsAuthPopupVisible, selectIsLoginPopupVisible } from 'src/app/account-management/auth.state';
import { hideAuthPopup, hideLoginPopup, showAuthPopup } from 'src/app/account-management/auth.actions';

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

  constructor(private authService: AuthService,
              private store: Store)
  {
    this.store.select(selectIsAuthPopupVisible).subscribe((ispx) => {this.isPopupVisible = ispx;});
    this.store.select(selectIsLoginPopupVisible).subscribe((ispx) => {this.isLoginPopupVisible = ispx;});
  }

  showPopup(event: MouseEvent)
  {
    if(event.target === event.currentTarget)
    {
      this.isPopupVisible ? this.store.dispatch(hideAuthPopup()) : this.store.dispatch(showAuthPopup());
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
    this.isButtonVisible = false;
    this.store.dispatch(hideAuthPopup());
  }

  showRegisterPopup()
  {
    this.store.dispatch(hideLoginPopup());
    this.isRegisterPopupVisible = true;
  }

  showLoginPopup()
  {
    this.isRegisterPopupVisible = false;
    this.store.dispatch(showAuthPopup());
  }
}
