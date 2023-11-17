import { Component } from '@angular/core';
import { AccountService } from '../services/account.service';
import { AuthService } from '../account-management/auth-service.service';
import { User } from '../account-management/user';

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
    console.log("token:");
    console.log(loginToken);
    console.log("key:");
    console.log(loginKey);
    if(loginToken && loginKey)
    {
      this.accountService.loginWithToken(loginToken, loginKey).subscribe({
        next: (response: any) => {
          console.log(response);
          const user: User = response as User;
          this.authService.login(user);
        },
        error:error => {
          console.log("Error");
          console.log(error);
        }
      })
    } else {
      console.log("No login token found.");
    }
  }

}
