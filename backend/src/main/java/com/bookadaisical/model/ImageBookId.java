package com.bookadaisical.model;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageBookId implements Serializable {
    private Long book;
    private Long image;
}
