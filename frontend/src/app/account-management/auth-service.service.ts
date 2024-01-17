import { Injectable } from '@angular/core';
import { Store } from '@ngrx/store';
import { login, logout, setTokenVerificationStatus } from './auth.actions';
import * as AuthState from '../account-management/auth.state'
import { UserToken } from '../elements/classes/userToken';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private store: Store<AuthState.AuthState>) {}

  login(username: string) {
    this.store.dispatch(login({ username }));
  }

  loginAndSaveToken(user: UserToken)
  {
    let username: string = user.username;
    this.store.dispatch(login({ username }));
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

  setTokenVerificationStatus(status: boolean)
  {
    this.store.dispatch(setTokenVerificationStatus({ status }));
  }
}
