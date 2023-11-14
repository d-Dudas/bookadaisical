import { Component} from '@angular/core';
import { AuthService } from '../../../account-management/auth-service.service';
import { Store } from '@ngrx/store';
import { selectIntendedpath, selectIsAuthPopupVisible, selectIsAuthenticated, selectIsLoginPopupVisible } from 'src/app/account-management/auth.state';
import { hideAuthPopup, hideLoginPopup, showAuthPopup } from 'src/app/account-management/auth.actions';
import { Router } from '@angular/router';

@Component({
  selector: 'app-authenticate-popup',
  templateUrl: './authenticate-popup.component.html',
  styleUrls: ['./authenticate-popup.component.css']
})
export class AuthenticatePopupComponent {
  isAuthenticated: boolean = false;
  isPopupVisible: boolean = false;
  isLoginPopupVisible: boolean = false;
  isRegisterPopupVisible: boolean = false;
  popupButtonText: string = this.isPopupVisible ? "Hide popup" : "Show popup";

  constructor(private authService: AuthService,
              private router: Router,
              private store: Store)
  {
    this.store.select(selectIsAuthPopupVisible).subscribe((is) => {this.isPopupVisible = is;});
    this.store.select(selectIsLoginPopupVisible).subscribe((is) => {this.isLoginPopupVisible = is;});
    this.store.select(selectIsAuthenticated).subscribe((is) => {this.isAuthenticated = is;});
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
    this.router.navigate(['/home']);
  }

  authenticateDone()
  {
    let intendedPath: string | boolean = false;

    this.store.select(selectIntendedpath).subscribe((path) => {intendedPath = path; console.log(path)});
    this.store.dispatch(hideAuthPopup());

    if(intendedPath){
      this.router.navigate([intendedPath]);
    }
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
