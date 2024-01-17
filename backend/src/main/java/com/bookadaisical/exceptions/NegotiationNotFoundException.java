package com.bookadaisical.exceptions;

public class NegotiationNotFoundException extends Exception {
    public NegotiationNotFoundException()
    {
        super("negotiation_not_found");
    }
}
