// TODO: remove this temporary component later
import { Component } from '@angular/core';
import { Store } from '@ngrx/store';
import { selectIsAuthenticated, selectUser } from '../account-management/auth.state';
import { UserSlim } from '../elements/classes/userSlim';

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent {
  isLoggedIn = false;
  user: UserSlim | null = null;

  constructor(private store: Store) {
    this.store.select(selectIsAuthenticated).subscribe((isAuthenticated) =>
    {
      this.isLoggedIn = isAuthenticated;
      if(isAuthenticated)
      {
        this.isLoggedIn = isAuthenticated;
        this.store.select(selectUser).subscribe((user) => {this.user = user});
      }
    })
  }
}
