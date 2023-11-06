import { Component, EventEmitter, Input, Output } from '@angular/core';
import { AbstractControlOptions, FormBuilder, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { AuthService } from 'src/app/account-management/auth-service.service';
import { User } from 'src/app/account-management/user';
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
      phoneNumber: ['', [Validators.required, Validators.pattern(/^07\d{8}$/)]],
      password: ['', [Validators.required, Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/)]],
      confirmPassword: ['', [Validators.required]]
    }, {
      validators: this.passwordMatchValidator
    } as AbstractControlOptions);
  }

  passwordMatchValidator(group: FormGroup): ValidationErrors | null {
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
    this.accountService.sendRegisterFormToBackend(this.registerFormData.getRawValue()).subscribe({
      next:(response: any) =>
      {
        const user: User = response as User;
        this.authService.login(user);
        this.clearRegisterFormData();
        this.authenticateDoneEvent.emit();
      },
      error:error =>
      {
        console.log("Backend error:");
        console.log(error);
      }
    })
  }

  clearRegisterFormData()
  {
    this.registerFormData = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(6)]],
      email: ['', [Validators.required, Validators.email]],
      phoneNumber: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]],
      password: ['', [Validators.required, Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/)]],
    });
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
}
