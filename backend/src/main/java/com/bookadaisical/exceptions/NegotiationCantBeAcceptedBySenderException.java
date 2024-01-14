package com.bookadaisical.exceptions;

public class NegotiationCantBeAcceptedBySenderException extends Exception {
    public NegotiationCantBeAcceptedBySenderException()
    {
        super("negotiation_cant_be_accepted_by_sender");
    }
}
