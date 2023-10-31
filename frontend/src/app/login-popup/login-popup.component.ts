import { Component } from '@angular/core';
import { AccountService } from '../account.service';
import { AuthService } from '../account-management/auth-service.service';
import { User } from '../account-management/user';

@Component({
  selector: 'app-login-popup',
  templateUrl: './login-popup.component.html',
  styleUrls: ['./login-popup.component.css']
})
export class LoginPopupComponent {

  isPopupVisible: boolean = false;
  popupButtonText: string = "Show popup";
  loginFormData = {
    accountIdentificator: '',
    accountPassword: ''
  };

  constructor(private accountService: AccountService,
              private authService: AuthService) {}

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

  isFormValid(form: any): boolean {
    return form.form.valid && this.loginFormData.accountIdentificator && this.loginFormData.accountPassword;
  }

  submitLoginForm()
  {
    console.log(this.loginFormData);
    this.accountService.sendLoginFormToBackend(this.loginFormData).subscribe({
      next:response =>
      {
        console.log("Backend response:");
        console.log(response);

        const user: User = {
          username: "Paperplanes",
          id: 13,
          email: "secret"
        }

        this.authService.login(user);
      },
      error:error =>
      {
        console.log("Backend error:");
        console.log(error);
      }
    })
  }
}
