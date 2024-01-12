package com.bookadaisical.dto.requests;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NegotiationOfferDto {
    private String initiatorUsername;
    private String responderUsername;
    private List<UUID> initiatorSelectedBooks;
    private List<UUID> responderSelectedBooks;

    @Override
    public String toString() {
        return "NegotiationOfferDto [initiatorUsername=" + initiatorUsername + ", responderUsername=" + responderUsername
                + ", initiatorSelectedBooks=" + initiatorSelectedBooks + ", responderSelectedBooks="
                + responderSelectedBooks + "]";
    }
}
