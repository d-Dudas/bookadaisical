package com.bookadaisical.service.interfaces;

import java.util.List;

import com.bookadaisical.dto.requests.NegotiatingUsersDto;
import com.bookadaisical.dto.requests.NegotiationOfferDto;
import com.bookadaisical.dto.requests.UsernameDto;
import com.bookadaisical.dto.responses.ExistingNegotiationOfferDto;
import com.bookadaisical.exceptions.UserNotFoundException;

public interface INegotiationService {
    public void sendOffer(NegotiationOfferDto negotiationOfferDto) throws Exception;
    public NegotiationOfferDto getExistingNegotiation(NegotiatingUsersDto negotiatingUsersDto) throws Exception;
    public void acceptOffer(NegotiatingUsersDto negotiatingUsersDto) throws Exception;
    public void cancelNegotiation(NegotiatingUsersDto negotiatingUsersDto) throws Exception;
    public List<ExistingNegotiationOfferDto> getUserOngoingNegotiations(UsernameDto usernameDto) throws UserNotFoundException;
}
