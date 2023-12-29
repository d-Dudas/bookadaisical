import { Component, EventEmitter, Input, Output } from '@angular/core';
import { AbstractControl, AbstractControlOptions, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { AuthService } from 'src/app/account-management/auth-service.service';
import { UserSlim } from 'src/app/elements/classes/userSlim';
import { UserToken } from 'src/app/elements/classes/userToken';
import { AccountService } from 'src/app/services/account.service';

@Component({
  selector: 'app-register-popup',
  templateUrl: './register-popup.component.html',
  styleUrls: ['./register-popup.component.css']
})
export class RegisterPopupComponent {
  @Input() isRegisterPopupVisible: boolean = false;
  @Output() authenticateDoneEvent = new EventEmitter<void>();
  @Output() showLoginPopupEvent = new EventEmitter<void>();
  registerFormData: FormGroup;
  isPasswordVisisble: boolean = false;
  isPasswordHidden: boolean = true;
  isConfirmPasswordHidden: boolean = true;
  passwordInputType: string = this.isPasswordVisisble ? "text" : "password";

  constructor(private accountService: AccountService,
    private authService: AuthService,
    private formBuilder: FormBuilder)
  {
    this.registerFormData = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(6)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, this.passwordStrengthValidator()]],
      confirmPassword: ['', [Validators.required, this.passwordsMatchValidator()]],
      rememberMe: [false]
    });
  }

  submitRegisterForm()
  {
    if(!this.registerFormData.valid) {
      return;
    }
    this.accountService.sendRegisterFormToBackend(this.registerFormData.getRawValue()).subscribe({
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

        this.clearRegisterFormData();
        this.authenticateDoneEvent.emit();
      },
      error:error =>
      {
        console.log(error);
      }
    })
  }

  private clearRegisterFormData(): void
  {
    this.registerFormData.reset({
      username: '',
      email: '',
      password: '',
      confirmPassword: '',
      rememberMe: false
    })
  }

  showLoginPopup()
  {
    this.showLoginPopupEvent.emit();
  }

  private passwordStrengthValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const value = control.value;
      const errors: ValidationErrors = {};

      if (!value) {
        return null;
      }

      if (value.length < 8) {
        errors['minLength'] = { requiredLength: 8, actualLength: value.length };
        return errors;
      }

      if (!/[A-Z]/.test(value)) {
        errors['uppercase'] = true;
        return errors;
      }

      if (!/[a-z]/.test(value)) {
        errors['lowercase'] = true;
        return errors;
      }

      if (!/\d/.test(value)) {
        errors['number'] = true;
        return errors;
      }

      return Object.keys(errors).length === 0 ? null : errors;
    };
  }

  private passwordsMatchValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const value = control.value;
      const errors: ValidationErrors = {};

      if(!value) {
        return null;
      }

      if(value !== this.registerFormData.getRawValue().password)
      {
        errors['mismatch'] = true;
        return errors;
      }

      return Object.keys(errors).length === 0 ? null : errors;
    }
  }
}
