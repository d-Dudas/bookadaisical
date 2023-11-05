import { Component, EventEmitter, Input, Output } from '@angular/core';
import { AuthService } from 'src/app/account-management/auth-service.service';
import { User } from 'src/app/account-management/user';
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
    identificator: '',
    password: ''
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
        const user: User = response as User;
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
    return form.form.valid && this.loginFormData.identificator && this.loginFormData.password;
  }

  clearLoginFormData()
  {
    this.loginFormData = {
      identificator: '',
      password: ''
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
