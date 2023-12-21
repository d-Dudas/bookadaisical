import { Component, EventEmitter, Input, Output } from '@angular/core';
import { AbstractControlOptions, FormBuilder, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { Store } from '@ngrx/store';
import { User } from 'src/app/elements/classes/user';
import { AccountService } from 'src/app/services/account.service';
import { login } from 'src/app/account-management/auth.actions';
import { UserSlim } from 'src/app/elements/classes/userSlim';

@Component({
  selector: 'app-account-settings-popup',
  templateUrl: './account-settings-popup.component.html',
  styleUrls: ['./account-settings-popup.component.css']
})
export class AccountSettingsPopupComponent {
  @Input() isAccountSettingsPopupVisible: boolean = false;
  @Input() user: User | null = null;
  @Output() closeAccountSettingsPopup = new EventEmitter<void>();

  changeUsernameFormData: FormGroup;
  changeEmailFormData: FormGroup;
  changePasswordFormData: FormGroup;
  isPasswordVisisble: boolean = false;
  passwordInputType: string = this.isPasswordVisisble ? "text" : "password";

  isChangeUsernameFormVisible: boolean = false;
  isChangeEmailFormVisible: boolean = false;
  isChangePasswordFormVisible: boolean = false;

  constructor(private formBuilder: FormBuilder,
      private accountService: AccountService,
      private store: Store)
  {
    this.changeUsernameFormData = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(6)]]
    });

    this.changeEmailFormData = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]]
    });

    this.changePasswordFormData = this.formBuilder.group({
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

  closePopup(event: MouseEvent)
  {
    if(event.target === event.currentTarget)
    {
      this.closeAccountSettingsPopup.emit();
    }
  }

  triggerUsernameForm(): void
  {
    this.changeUsernameFormData.reset({
      username: ''
    });

    this.isChangeUsernameFormVisible = !this.isChangeUsernameFormVisible;
  }

  submitChangeUsernameForm(): void
  {
    if(!this.changeUsernameFormData.valid) return;
    if(this.user !== null)
    {
      this.accountService.changeUsername(this.user.id, this.changeUsernameFormData.value.username).subscribe({
        next: user => {
          let username = user.username;
          this.store.dispatch(login({ username }));
          window.location.reload();
        },
        error: error => {
          console.log(error);
        }
      })
    }
  }

  triggerEmailForm(): void
  {
    this.changeEmailFormData.reset({
      email: ''
    });

    this.isChangeEmailFormVisible = !this.isChangeEmailFormVisible;
  }

  submitChangeEmailForm(): void
  {
    if(!this.changeEmailFormData.valid) return;
    if(this.user !== null)
    {
      this.accountService.changeEmail(this.user.id, this.changeEmailFormData.value.email).subscribe({
        next: user => {
          let username = user.username;
          this.store.dispatch(login({ username }));
          window.location.reload();
        },
        error: error => {
          console.log(error);
        }
      })
    }
  }

  triggerPasswordForm(): void
  {
    this.changePasswordFormData.reset({
      password: '',
      confirmPassword: ''
    });

    this.isChangePasswordFormVisible = !this.isChangePasswordFormVisible;
  }

  submitChangePasswordForm(): void
  {
    if(!this.changePasswordFormData.valid) return;
    if(this.user !== null)
    {
      this.accountService.changePassword(this.user.id, this.changePasswordFormData.value.password).subscribe({
        next: user => {
          let username: string = user.username;
          this.store.dispatch(login({ username }));
          window.location.reload();
        },
        error: error => {
          console.log(error);
        }
      })
    }
  }

  togglePasswordVisibility()
  {
    this.isPasswordVisisble = !this.isPasswordVisisble;
    this.passwordInputType = this.isPasswordVisisble ? "text" : "password";
  }
}
