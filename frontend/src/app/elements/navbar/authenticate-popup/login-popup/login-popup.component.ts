import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { AuthService } from 'src/app/account-management/auth-service.service';
import { UserSlim } from 'src/app/elements/classes/userSlim';
import { UserToken } from 'src/app/elements/classes/userToken';
import { AccountService } from 'src/app/services/account.service';

@Component({
  selector: 'app-login-popup',
  templateUrl: './login-popup.component.html',
  styleUrls: ['./login-popup.component.css']
})
export class LoginPopupComponent {
  @Input() isLoginPopupVisible: boolean = false;
  @Output() authenticateDoneEvent = new EventEmitter<void>();
  @Output() showRegisterPopupEvent = new EventEmitter<void>();
  loginFormData = {
    identifier: '',
    password: '',
    rememberMe: false
  };
  isPasswordVisisble: boolean = false;
  passwordInputType: string = this.isPasswordVisisble ? "text" : "password";

  constructor(private accountService: AccountService,
              private authService: AuthService) {}

  submitLoginForm()
  {
    if(!this.isFormValid())
    {
      return;
    }
    this.accountService.sendLoginFormToBackend(this.loginFormData).subscribe({
      next:(response: any) =>
      {
        if(response.token !== null)
        {
          const user: UserToken = response as UserToken;
          this.authService.loginAndSaveToken(user);
        }
        else
        {
          const user: UserSlim = response as UserSlim;
          this.authService.login(user);
        }

        this.clearLoginFormData();
        this.authenticateDoneEvent.emit();
      },
      error:error =>
      {
        console.log(error);
      }
    })
  }

  isFormValid(): boolean
  {
    return this.loginFormData.identifier != '' && this.loginFormData.password != '';
  }

  clearLoginFormData()
  {
    this.loginFormData = {
      identifier: '',
      password: '',
      rememberMe: false
    };
  }

  showRegisterPopup()
  {
    this.showRegisterPopupEvent.emit();
  }

  togglePasswordVisibility()
  {
    this.isPasswordVisisble = !this.isPasswordVisisble;
    this.passwordInputType = this.isPasswordVisisble ? "text" : "password";
  }

  toggleRememberMe()
  {
    this.loginFormData.rememberMe = !this.loginFormData.rememberMe;
  }
}
