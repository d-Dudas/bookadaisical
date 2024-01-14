package com.bookadaisical.service.interfaces;

import com.bookadaisical.dto.requests.NegotiatingUsersDto;
import com.bookadaisical.dto.requests.NegotiationOfferDto;

public interface INegotiationService {
    public void sendOffer(NegotiationOfferDto negotiationOfferDto) throws Exception;
    public NegotiationOfferDto getExistingNegotiation(NegotiatingUsersDto negotiatingUsersDto) throws Exception;
    public void acceptOffer(NegotiatingUsersDto negotiatingUsersDto) throws Exception;
    public void cancelNegotiation(NegotiatingUsersDto negotiatingUsersDto) throws Exception;
}
