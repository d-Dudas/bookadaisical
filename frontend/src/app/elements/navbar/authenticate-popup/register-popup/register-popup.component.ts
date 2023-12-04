import { Component, EventEmitter, Input, Output } from '@angular/core';
import { AbstractControlOptions, FormBuilder, FormGroup, ValidationErrors, Validators } from '@angular/forms';
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
  passwordInputType: string = this.isPasswordVisisble ? "text" : "password";

  constructor(private accountService: AccountService,
    private authService: AuthService,
    private formBuilder: FormBuilder)
  {
    this.registerFormData = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(6)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/)]],
      confirmPassword: ['', [Validators.required]],
      rememberMe: [false]
    }, {
      validators: this.passwordMatchValidator
    } as AbstractControlOptions);
  }

  private passwordMatchValidator(group: FormGroup): ValidationErrors | null {
    const password = group.get('password')?.value;
    const confirmPassword = group.get('confirmPassword')?.value;

    if (password === confirmPassword) {
      return null;
    } else {
      return { mismatch: true };
    }
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
          this.authService.login(user);
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

  togglePasswordVisibility()
  {
    this.isPasswordVisisble = !this.isPasswordVisisble;
    this.passwordInputType = this.isPasswordVisisble ? "text" : "password";
  }

  toggleRememberMe(event: any)
  {
    const isChecked = event.target.checked;
    this.registerFormData.get('rememberMe')?.setValue(isChecked);
  }
}
