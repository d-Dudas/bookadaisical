import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from 'src/app/elements/classes/user';

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

  constructor(private formBuilder: FormBuilder)
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
    console.log(this.changeUsernameFormData);
  }
}
