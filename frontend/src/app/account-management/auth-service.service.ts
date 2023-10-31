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
    this.store.dispatch(login({ user }));
  }

  logout() {
    this.store.dispatch(logout());
  }

  isAuthenticated() {
    return AuthState.selectIsAuthenticated;
  }
}
