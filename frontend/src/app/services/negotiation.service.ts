import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, last } from 'rxjs';
import { NegotiatingUsersDto } from '../elements/interfaces/find-existing-negotiation-dto';
import { NegotiationOfferDto } from '../elements/interfaces/negotiation-offer-dto';
import { ExistingNegotiationOfferDto } from '../elements/interfaces/existing-negotiation-offer-dto';

@Injectable({
  providedIn: 'root'
})
export class NegotiationService {
  private backendUrl = 'http://localhost:8080/negotiations';
  private sendOfferUrl = '/send-offer';
  private getExistingNegotiationUrl = '/get-existing-negotiation';
  private acceptOfferUrl = '/accept-offer';
  private cancelNegotiationUrl = '/cancel-negotiation';
  private userOngoingNegotiationsUrl = '/user-negotiations';

  private storedBookId = '';
  private responderUsername = '';

  constructor(private http: HttpClient)
  {
    let lastResponderUsername = localStorage.getItem('lastResponderUsername');
    if(lastResponderUsername !== null) this.responderUsername = lastResponderUsername;
  }

  sendOffer(negotiationOffer: NegotiationOfferDto)
  {
    return this.http.post<any>(this.backendUrl + this.sendOfferUrl, negotiationOffer);
  }

  getExistingNegotiation(users: NegotiatingUsersDto): Observable<ExistingNegotiationOfferDto>
  {
    return this.http.post<ExistingNegotiationOfferDto>(this.backendUrl + this.getExistingNegotiationUrl, users);
  }

  acceptOffer(users: NegotiatingUsersDto)
  {
    return this.http.post(this.backendUrl + this.acceptOfferUrl, users);
  }

  cancelNegotiation(users: NegotiatingUsersDto)
  {
    return this.http.post(this.backendUrl + this.cancelNegotiationUrl, users);
  }

  getUserOngoingNegotiations(username: string) : Observable<ExistingNegotiationOfferDto[]>
  {
    return this.http.post<ExistingNegotiationOfferDto[]>(this.backendUrl + this.userOngoingNegotiationsUrl, { username: username });
  }

  setStoredBookId(bookId: string): void
  {
    this.storedBookId = bookId;
  }

  getStoredBookId(): string
  {
    return this.storedBookId;
  }

  setResponderUsername(username: string): void
  {
    this.responderUsername = username;
    localStorage.setItem('lastResponderUsername', username);
  }

  getResponderUsername(): string
  {
    return this.responderUsername;
  }
}
