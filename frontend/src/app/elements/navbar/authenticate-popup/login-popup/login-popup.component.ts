import { Component, EventEmitter, Input, Output } from '@angular/core';
import { AbstractControl, FormBuilder, ValidationErrors, Validators } from '@angular/forms';
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
  loginFormData = this.formBuilder.group({
    identifier: ['', [Validators.required, this.customIdentifierValidator.bind(this)]],
    password: ['', [Validators.required, this.customPasswordValidator.bind(this)]],
    rememberMe: false
  });

  errors = {
    wrongIdentificator: false,
    wrongPassword: false
  };

  isPasswordHidden: boolean = true;

  constructor(private accountService: AccountService,
              private authService: AuthService,
              private formBuilder: FormBuilder) {}

  submitLoginForm()
  {
    if(!this.loginFormData.valid)
    {
      return;
    }
    this.errors.wrongIdentificator = false;
    this.errors.wrongPassword = false;
    this.accountService.sendLoginFormToBackend(this.loginFormData.getRawValue()).subscribe({
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
          this.authService.login(user.username);
        }

        this.clearLoginFormData();
        this.authenticateDoneEvent.emit();
      },
      error:error =>
      {
        if(error.error === "invalid_password")
        {
          this.errors.wrongPassword = true;
        }
        else if (error.error === "user_not_found")
        {
          this.errors.wrongIdentificator = true;
        }

        this.loginFormData.controls['identifier'].updateValueAndValidity();
        this.loginFormData.controls['password'].updateValueAndValidity();
      }
    })
  }

  customPasswordValidator(control: AbstractControl): ValidationErrors | null {
    if (this.errors?.wrongPassword) {
      return { invalidPassword: true };
    }
    return null;
  }

  customIdentifierValidator(control: AbstractControl): ValidationErrors | null {
    if (this.errors?.wrongIdentificator) {
      return { invalidIdentifier: true };
    }
    return null;
  }

  clearLoginFormData()
  {
    this.loginFormData.reset();
  }

  showRegisterPopup()
  {
    this.showRegisterPopupEvent.emit();
  }

  updateIdentificatorError()
  {
    this.errors.wrongIdentificator = false;
    this.loginFormData.controls['identifier'].updateValueAndValidity();
  }

  updatePasswordError()
  {
    this.errors.wrongPassword = false;
    this.loginFormData.controls['password'].updateValueAndValidity();
  }
}
