import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  private backendUrl = 'http://localhost:8080';
  private registerAccountAddress = '/register-account';
  private loginAddress = '/login';

  constructor(private http: HttpClient) { }

  sendRegisterAccountFormToBackend(data: string)
  {
    return this.http.post(this.backendUrl + this.registerAccountAddress, data, {
      headers: {'Content-Type': 'text/plain'},
      responseType: 'text'
    });
  }

  sendLoginFormToBackend(loginFormData: {})
  {
    return this.http.post(this.backendUrl + this.loginAddress, loginFormData);
  }

  sendRegisterFormToBackend(registerFormData : {})
  {
    console.log(registerFormData);
    return this.http.post(this.backendUrl + this.registerAccountAddress, registerFormData);
  }
}
