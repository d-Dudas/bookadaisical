package com.bookadaisical.model;

import java.io.Serializable;
import java.util.UUID;

import com.bookadaisical.enums.TradingOption;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TradingOptionBookId implements Serializable {
    private UUID book;
    private TradingOption tradingOption;
}
