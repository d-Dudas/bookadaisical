import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { selectIsAuthenticated, selectUser } from 'src/app/account-management/auth.state';
import { User } from 'src/app/elements/classes/user';
import { AccountService } from 'src/app/services/account.service';

@Component({
  selector: 'app-account-page',
  templateUrl: './account-page.component.html',
  styleUrls: ['./account-page.component.css']
})
export class AccountPageComponent {

  public user: User | null = null;
  public isVisitorTheOwner: boolean = false;
  public isAccountSettingsPopupVisible = true;

  constructor(private accountService: AccountService,
              private route: ActivatedRoute,
              private router: Router,
              private store: Store)
  {
    const userId: number = this.getUserIdFromUrl();
    this.accountService.getUserDetails(userId).subscribe({
      next: user => {
        this.user = user;
        this.verifyIfVisitorIsTheOwner();
      },
      error: () => {
        this.router.navigate(["home"]);
      }
    });
  }

  getUserIdFromUrl(): number
  {
    let userId: number = -1;
    this.route.paramMap.subscribe({
      next: params => {
        const userIdString: string | null = params.get('userId');
        if(userIdString !== null)
        {
          userId = parseInt(userIdString, 10);
        } else {
          this.router.navigate(["/home"]);
        }
      }
    });

    return userId;
  }

  verifyIfVisitorIsTheOwner()
  {
    this.store.select(selectIsAuthenticated).subscribe({
      next: (isAuthenticated) => {
        if(isAuthenticated)
        {
          this.store.select(selectUser).subscribe({
            next: (user) => {
              if(user !== null)
              {
                this.isVisitorTheOwner = user.id == this.user?.id;
              }
            }
          });
        }
      }
    });
  }

  openAccountSettingsPopup()
  {
    this.isAccountSettingsPopupVisible = true;
  }

  closeAccountSettingsPopup()
  {
    this.isAccountSettingsPopupVisible = false;
  }
}
