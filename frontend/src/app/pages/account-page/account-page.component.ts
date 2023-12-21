import { Component, ViewChild } from '@angular/core';
import { MatDrawer } from '@angular/material/sidenav';
import { ActivatedRoute, Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { selectIsAuthenticated, selectUser } from 'src/app/account-management/auth.state';
import { User } from 'src/app/elements/classes/user';
import { UserSlim } from 'src/app/elements/classes/userSlim';
import { AccountService } from 'src/app/services/account.service';

@Component({
  selector: 'app-account-page',
  templateUrl: './account-page.component.html',
  styleUrls: ['./account-page.component.css'],
})
export class AccountPageComponent {
  public user: User | null = null;
  public visitor: UserSlim | null = null;
  public isVisitorTheOwner: boolean = false;
  public isAccountSettingsPopupVisible = false;

  constructor(private accountService: AccountService,
              private route: ActivatedRoute,
              private router: Router,
              private store: Store)
  {
    const username: string = this.getUserIdFromUrl();
    this.accountService.getUserDetails(username).subscribe({
      next: user => {
        this.user = user;
        this.verifyIfVisitorIsTheOwner();
      },
      error: () => {
        this.router.navigate(["home"]);
      }
    });
  }

  getUserIdFromUrl(): string
  {
    let username: string = "";
    this.route.paramMap.subscribe({
      next: params => {
        const tempUsername: string | null = params.get('userId');
        if(tempUsername !== null)
        {
          username = tempUsername;
        } else {
          this.router.navigate(["/home"]);
        }
      }
    });

    return username;
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
                this.isVisitorTheOwner = user.username == this.user?.username;
                this.visitor = user;
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

  getUser(): UserSlim {
    if(this.user !== null)
    {
      let user: User = this.user;
      let userSlim: UserSlim = {
        username: user.username
      }

      return userSlim;
    }
    let emptyUser: UserSlim = {username: ""};
    return emptyUser;
  }

  getVisitor(): UserSlim {
    if(this.visitor !== null)
    {
      return this.visitor;
    }
    let emptyUser: UserSlim = {username: ""};
    return emptyUser;
  }
}
