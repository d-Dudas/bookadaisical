import { Component } from '@angular/core';
import { Store } from '@ngrx/store';
import { selectIsAuthenticated, selectUser } from 'src/app/account-management/auth.state';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  isAuthenticated: boolean = false;
  username: string = "";

  constructor(private store: Store){}

  ngOnInit()
  {
    this.store.select(selectIsAuthenticated).subscribe((is) => {this.isAuthenticated = is;});
    this.store.select(selectUser).subscribe((username) => {this.username = username!});
  }
}
