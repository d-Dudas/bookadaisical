package com.bookadaisical.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookadaisical.dto.requests.NegotiationOfferDto;

@RestController
public class NegotiationController {
    @PostMapping("/send-offer")
    public ResponseEntity<?> sendOffer(@RequestBody NegotiationOfferDto negotiationOfferDto)
    {
        return null;
        //TODO return negotiationOfferDto.getInitiatorId() % 2 == 0 ? ResponseEntity.badRequest().body("helloWorld") : ResponseEntity.ok("helloWorld");
    }
}
