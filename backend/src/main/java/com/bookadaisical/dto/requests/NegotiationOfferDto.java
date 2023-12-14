package com.bookadaisical.dto.requests;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NegotiationOfferDto {
    private UUID initiatorId;
    private UUID responderId;
    private List<UUID> initiatorSelectedBooks;
    private List<UUID> responderSelectedBooks;
}
