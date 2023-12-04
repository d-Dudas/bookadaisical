import { Injectable } from '@angular/core';
import { Store } from '@ngrx/store';
import { login, logout } from './auth.actions';
import * as AuthState from '../account-management/auth.state'
import { UserSlim } from '../elements/classes/userSlim';
import { UserToken } from '../elements/classes/userToken';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private store: Store<AuthState.AuthState>) {}

  login(user: UserSlim) {
    this.store.dispatch(login({ user }));
  }

  loginAndSaveToken(user: UserToken)
  {
    this.store.dispatch(login({ user }));
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
