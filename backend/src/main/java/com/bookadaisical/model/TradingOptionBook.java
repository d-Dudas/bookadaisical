package com.bookadaisical.model;

import com.bookadaisical.enums.TradingOption;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@IdClass(TradingOptionBookId.class)
@Table(schema = "bookadaisical", name = "trading_options_books")
public class TradingOptionBook {
    
    @Id
    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false)
    private Book book;

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "trading_option", nullable = false)
    private TradingOption tradingOption;
}
