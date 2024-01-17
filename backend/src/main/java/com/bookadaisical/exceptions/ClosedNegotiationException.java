package com.bookadaisical.exceptions;

public class ClosedNegotiationException extends Exception {
    public ClosedNegotiationException()
    {
        super("negotiation_is_closed");
    }
}
