package com.bookadaisical.dto.responses;

import com.bookadaisical.dto.requests.NegotiationOfferDto;
import com.bookadaisical.enums.NegotiationStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ExistingNegotiationOfferDto extends NegotiationOfferDto {
    NegotiationStatus status;
}
