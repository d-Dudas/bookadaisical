import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

export interface NegotiationOfferDto {
    initiatorId: number,
    responderId: number,
    initiatorSelectedBooks: number[],
    responderSelectedBooks: number[]
}

@Injectable({
  providedIn: 'root'
})
export class NegotiationService {
  private backendUrl = 'http://localhost:8080';
  private sendOfferUrl = '/send-offer'

  constructor(private http: HttpClient)
  {
  }

  sendOffer(negotiationOffer: NegotiationOfferDto): void
  {
    this.http.post<any>(this.backendUrl + this.sendOfferUrl, negotiationOffer).subscribe(
    );
  }
}
