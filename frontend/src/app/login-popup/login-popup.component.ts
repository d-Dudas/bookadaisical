import { Component, EventEmitter, Input, Output } from '@angular/core';
import { AccountService } from '../account.service';
import { AuthService } from '../account-management/auth-service.service';
import { User } from '../account-management/user';

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
    accountIdentificator: '',
    accountPassword: ''
  };
  isPasswordVisisble: boolean = false;
  passwordInputType: string = this.isPasswordVisisble ? "text" : "password";

  constructor(private accountService: AccountService,
              private authService: AuthService) {}

  submitLoginForm()
  {
    console.log(this.loginFormData);
    this.accountService.sendLoginFormToBackend(this.loginFormData).subscribe({
      next:(response: any) =>
      {
        console.log("Backend response:");
        console.log(response);

        const user: User = response as User;

        console.log("User:");
        console.log(user);

        this.authService.login(user);
        this.clearLoginFormData();
        this.authenticateDoneEvent.emit();
      },
      error:error =>
      {
        console.log("Backend error:");
        console.log(error);
      }
    })
  }

  isFormValid(form: any): boolean
  {
    return form.form.valid && this.loginFormData.accountIdentificator && this.loginFormData.accountPassword;
  }

  clearLoginFormData()
  {
    this.loginFormData = {
      accountIdentificator: '',
      accountPassword: ''
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
}
