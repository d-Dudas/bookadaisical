import { Component, EventEmitter, Input, Output } from '@angular/core';
import { AbstractControl, AbstractControlOptions, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { Store } from '@ngrx/store';
import { User } from 'src/app/elements/classes/user';
import { AccountService } from 'src/app/services/account.service';
import { login } from 'src/app/account-management/auth.actions';
import { UserSlim } from 'src/app/elements/classes/userSlim';
import { ChangeProfilePictureDto } from 'src/app/elements/interfaces/change-profile-picture-dto';

@Component({
  selector: 'app-account-settings-popup',
  templateUrl: './account-settings-popup.component.html',
  styleUrls: ['./account-settings-popup.component.css']
})
export class AccountSettingsPopupComponent {
  @Input() isAccountSettingsPopupVisible: boolean = false;
  @Input() user: User | null = null;
  @Output() closeAccountSettingsPopup = new EventEmitter<void>();
  public hidePassword = true;
  public hideConfirmPassword = true;
  public changeUsernameFormData: FormGroup;
  public changeEmailFormData: FormGroup;
  public changePasswordFormData: FormGroup;
  public changeProfilePictureFormData: FormGroup;

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
      password: ['', [Validators.required, this.passwordStrengthValidator()]],
      confirmPassword: ['', [Validators.required, this.passwordsMatchValidator()]]
    });

    this.changeProfilePictureFormData = this.formBuilder.group({
      image: ['', Validators.required]
    });
  }

  public onFilesSelected(event: any): void {
    console.log(event);
    const file = event.target.files[0];
    const reader = new FileReader();
    reader.onload = (e: any) => {
      this.changeProfilePictureFormData.controls['image'].setValue(e.target.result);
    };
    reader.readAsDataURL(file);
  }

  public closePopup(event: MouseEvent)
  {
    if(event.target === event.currentTarget)
    {
      this.closeAccountSettingsPopup.emit();
    }
  }

  public submitChangeUsernameForm(): void
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

  public submitChangeEmailForm(): void
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

  public submitChangePasswordForm(): void
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

  public submitChangeProfilePictureForm(): void
  {
    if(!this.changeProfilePictureFormData.valid) return;
    console.log(this.changeProfilePictureFormData.getRawValue());

    let changeProfilePictureDto: ChangeProfilePictureDto = {
      username: this.user?.username!,
      image: this.extractBase64Data(this.changeProfilePictureFormData.controls['image'].value)
    }

    this.accountService.changeProfilePicture(changeProfilePictureDto).subscribe({
      next: () => {
        console.log("success");
      },
      error: (error) => {
        console.log(error);
      }
    })
  }

  private extractBase64Data(base64String: string): string {
    const commaIndex = base64String.indexOf(',');
    if (commaIndex !== -1 && commaIndex + 1 < base64String.length) {
      return base64String.substring(commaIndex + 1);
    }

    return base64String;
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

      if(value !== this.changePasswordFormData.getRawValue().password)
      {
        errors['mismatch'] = true;
        return errors;
      }

      return Object.keys(errors).length === 0 ? null : errors;
    }
  }
}
