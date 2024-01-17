import { Component } from '@angular/core';
import { AccountService } from '../services/account.service';
import { AuthService } from '../account-management/auth-service.service';
import { UserSlim } from '../elements/classes/userSlim';

@Component({
  selector: 'app-root',
  templateUrl: './root.component.html',
  styleUrls: ['./root.component.css']
})
export class RootComponent {

  constructor(private accountService: AccountService,
              private authService: AuthService){}

  ngOnInit(): void {
    let loginToken = localStorage.getItem('loginToken');
    let loginKey = localStorage.getItem('loginKey');

    if(loginToken && loginKey)
    {
      this.accountService.loginWithToken(loginToken, loginKey).subscribe({
        next: (response: any) => {
          this.authService.login(response.username);
          this.authService.setTokenVerificationStatus(true);
        },
        error: (error) => {
          this.authService.setTokenVerificationStatus(true);
        }
      });
    }
  }

}
