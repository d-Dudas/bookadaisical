package com.bookadaisical.model;

import java.io.Serializable;

import com.bookadaisical.enums.TradingOption;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TradingOptionBookId implements Serializable {
    private Long book;
    private TradingOption tradingOption;
}
