import { Component } from '@angular/core';
import { AccountService } from '../account.service';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})
export class LandingPageComponent {
  formData: any = {};

  constructor(private accountService: AccountService) {}

  submitRegisterAccountForm() {
    console.log("Sending to backend:");
    console.log(this.formData);
    this.accountService.sendRegisterAccountFormToBackend(this.formData).subscribe({
      next:response => {console.log("Backend response:"); console.log(response)},
      error:error => {console.log("Backend error:"); console.log(error);}
    })
  };
}
