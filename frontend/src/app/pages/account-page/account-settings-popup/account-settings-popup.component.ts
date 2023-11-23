import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
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

  isChangeUsernameFormVisible: boolean = false;

  constructor(private formBuilder: FormBuilder,
      private accountService: AccountService,
      private store: Store)
  {
    this.changeUsernameFormData = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(6)]]
    });
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
    this.isChangeUsernameFormVisible = !this.isChangeUsernameFormVisible;
  }

  submitChangeUsernameForm(): void
  {
    console.log(this.changeUsernameFormData.value.username);
    if(this.user !== null)
    {
      this.accountService.changeUsername(this.user.id, this.changeUsernameFormData.value.username).subscribe({
        next: user1 => {
          let user = new UserSlim(user1.id, user1.username, "", "");
          this.store.dispatch(login({ user }));
          window.location.reload();
        },
        error: error => {
          console.log(error);
        }
      })
    }
  }
}
