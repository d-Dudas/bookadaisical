package com.bookadaisical.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookadaisical.dto.requests.NegotiatingUsersDto;
import com.bookadaisical.dto.requests.NegotiationOfferDto;
import com.bookadaisical.dto.responses.ExistingNegotiationOfferDto;
import com.bookadaisical.service.NegotiationService;

@RestController
@RequestMapping("/negotiations")
public class NegotiationController {

    @Autowired
    private NegotiationService negotiationService;

    @PostMapping("/send-offer")
    public ResponseEntity<?> sendOffer(@RequestBody NegotiationOfferDto negotiationOfferDto)
    {
        System.out.println(negotiationOfferDto);
        try {
            negotiationService.sendOffer(negotiationOfferDto);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/get-existing-negotiation")
    public ResponseEntity<?> getExistingNegotiation(@RequestBody NegotiatingUsersDto negotiatingUsersDto)
    {
        try {
            ExistingNegotiationOfferDto existingNegotiationOfferDto = negotiationService.getExistingNegotiation(negotiatingUsersDto);
            return ResponseEntity.ok(existingNegotiationOfferDto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/accept-offer")
    public ResponseEntity<?> acceptOffer(@RequestBody NegotiatingUsersDto negotiatingUsersDto)
    {
        try {
            negotiationService.acceptOffer(negotiatingUsersDto);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/cancel-negotiation")
    public ResponseEntity<?> cancelNegotiation(@RequestBody NegotiatingUsersDto negotiatingUsersDto)
    {
        try {
            negotiationService.cancelNegotiation(negotiatingUsersDto);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
