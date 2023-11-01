import { Component, EventEmitter, Input, Output } from '@angular/core';
import { AccountService } from '../account.service';
import { AuthService } from '../account-management/auth-service.service';
import { User } from '../account-management/user';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

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

  constructor(private accountService: AccountService,
    private authService: AuthService,
    private formBuilder: FormBuilder)
  {
    this.registerFormData = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(6)]],
      email: ['', [Validators.required, Validators.email]],
      phoneNumber: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]],
      password: ['', [Validators.required, Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/)]]
    })
  }

  submitRegisterForm()
  {
    console.log(this.registerFormData);
    this.accountService.sendLoginFormToBackend(this.registerFormData).subscribe({
      next:(response: any) =>
      {
        console.log("Backend response:");
        console.log(response);

        const user: User = response as User;

        console.log("User:");
        console.log(user);

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

}
