import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../elements/classes/user';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  private backendUrl = 'http://localhost:8080';
  private registerAccountAddress = '/register';
  private loginAddress = '/login';
  private loginWithTokenUrl = '/login-with-token';
  private getUserDetailsUrl: string = '/get-user-details';
  private changeUsernameUrl: string = '/change-username';
  private changeEmailUrl: string = '/change-email';
  private changePasswordUrl: string = '/change-password';

  constructor(private http: HttpClient) { }

  sendLoginFormToBackend(loginFormData: {})
  {
    return this.http.post(this.backendUrl + this.loginAddress, loginFormData);
  }

  sendRegisterFormToBackend(registerFormData : {})
  {
    return this.http.post(this.backendUrl + this.registerAccountAddress, registerFormData);
  }

  loginWithToken(token: string, key: string)
  {
    return this.http.post(this.backendUrl + this.loginWithTokenUrl, {token, key});
  }

  getUserDetails(userId: number): Observable<User>
  {
    return this.http.get<User>(this.backendUrl + this.getUserDetailsUrl + "/" + userId);
  }

  changeUsername(userId: number, newUsername: string): Observable<User>
  {
    return this.http.post<User>(this.backendUrl + this.changeUsernameUrl, {userId, newUsername});
  }

  changeEmail(userId: number, newEmail: string): Observable<User>
  {
    return this.http.post<User>(this.backendUrl + this.changeEmailUrl, {userId, newEmail});
  }

  changePassword(userId: number, newPassword: string): Observable<User>
  {
    return this.http.post<User>(this.backendUrl + this.changePasswordUrl, {userId, newPassword});
  }
}
