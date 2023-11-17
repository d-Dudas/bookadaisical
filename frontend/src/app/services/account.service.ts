import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  private backendUrl = 'http://localhost:8080';
  private registerAccountAddress = '/register';
  private loginAddress = '/login';
  private loginWithTokenUrl = '/login-with-token';

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
}
