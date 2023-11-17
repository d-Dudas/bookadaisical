import { Injectable } from '@angular/core';
import { Store } from '@ngrx/store';
import { login, logout } from './auth.actions';
import { User } from './user';
import * as AuthState from '../account-management/auth.state'

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private store: Store<AuthState.AuthState>) {}

  login(user: User) {
    console.log("setting user");
    this.store.select(AuthState.selectIsAuthenticated).subscribe((is) => {console.log(is)});
    this.store.dispatch(login({ user }));
    this.store.select(AuthState.selectIsAuthenticated).subscribe((is) => {console.log(is)});
    this.store.select(AuthState.selectUser).subscribe((user) => {console.log(user)});
    localStorage.setItem('loginToken', user.token);
    localStorage.setItem('loginKey', user.key);
  }

  logout() {
    this.store.dispatch(logout());
    localStorage.removeItem('loginToken');
    localStorage.removeItem('loginKey');
  }

  isAuthenticated() {
    return AuthState.selectIsAuthenticated;
  }
}
