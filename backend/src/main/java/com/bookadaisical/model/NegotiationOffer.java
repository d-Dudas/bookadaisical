package com.bookadaisical.model;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import com.bookadaisical.enums.NegotiationStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class NegotiationOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User initiator;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User responder;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime lastUpdated;

    @ManyToMany
    @JoinTable(
        joinColumns = @JoinColumn(name = "negotiation_id"), 
        inverseJoinColumns = @JoinColumn(name = "selected_books_by_initiator"))
    Set<Book> initiatorSelectedBooks;

    @ManyToMany
    @JoinTable(
        joinColumns = @JoinColumn(name = "negotiation_id"), 
        inverseJoinColumns = @JoinColumn(name = "selected_books_by_responder"))
    Set<Book> responderSelectedBooks;

    @Enumerated(EnumType.STRING)
    private NegotiationStatus negotiationStatus;

    public NegotiationOffer(
        User initiator,
        User responder,
        Set<Book> initiatorSelectedBooks,
        Set<Book> responderSelectedBooks
    )
    {
        this.initiator = initiator;
        this.responder = responder;
        this.initiatorSelectedBooks = initiatorSelectedBooks;
        this.responderSelectedBooks = responderSelectedBooks;
        this.lastUpdated = LocalDateTime.now();
        negotiationStatus = NegotiationStatus.ONGOING;
    }

    public NegotiationOffer(
        User initiator,
        User responder,
        Set<Book> initiatorSelectedBooks,
        Set<Book> responderSelectedBooks,
        NegotiationStatus negotiationStatus
    )
    {
        this.initiator = initiator;
        this.responder = responder;
        this.initiatorSelectedBooks = initiatorSelectedBooks;
        this.responderSelectedBooks = responderSelectedBooks;
        this.lastUpdated = LocalDateTime.now();
        this.negotiationStatus = negotiationStatus;
    }
}
