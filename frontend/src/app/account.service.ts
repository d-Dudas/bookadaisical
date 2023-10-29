import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  private backendUrl = 'http://localhost:8080/register-account';

  constructor(private http: HttpClient) { }

  sendRegisterAccountFormToBackend(data: string)
  {
    return this.http.post(this.backendUrl, data, {
      headers: {'Content-Type': 'text/plain'},
      responseType: 'text'
    });
  }
}
