package com.bookadaisical.dto.responses;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthorsDto {
    private Set<String> authors;

    public AuthorsDto()
    {
        this.authors = new HashSet<>();
    }
}
