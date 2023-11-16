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
    identifier: '',
    password: ''
  };
  isPasswordVisisble: boolean = false;
  passwordInputType: string = this.isPasswordVisisble ? "text" : "password";

  constructor(private accountService: AccountService,
              private authService: AuthService) {}

  submitLoginForm()
  {
    if(!this.isFormValid())
    {
      console.log("Form is not valid. Won't submit.");
      return;
    }
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

  isFormValid(): boolean
  {
    // return form.form.valid &&
    return this.loginFormData.identifier != '' && this.loginFormData.password != '';
  }

  clearLoginFormData()
  {
    this.loginFormData = {
      identifier: '',
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
