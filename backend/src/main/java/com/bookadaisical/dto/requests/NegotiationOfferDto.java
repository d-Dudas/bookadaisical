package com.bookadaisical.dto.requests;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NegotiationOfferDto {
    private Integer initiatorId;
    private Integer responderId;
    private List<Integer> initiatorSelectedBooks;
    private List<Integer> responderSelectedBooks;
}
