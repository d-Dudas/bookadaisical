package com.bookadaisical.dto.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class NegotiatingUsersDto {
    String initiatorUsername;
    String responderUsername;

    @Override
    public String toString() {
        return "FindExistingNegotiationDto [initiatorUsername=" + initiatorUsername + ", responderUsername="
                + responderUsername + "]";
    }
}
