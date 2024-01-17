package com.bookadaisical.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bookadaisical.model.NegotiationOffer;
import com.bookadaisical.model.User;

public interface NegotiationOfferRepository extends JpaRepository<NegotiationOffer, UUID> {
    @Query("SELECT offer FROM NegotiationOffer offer WHERE (offer.initiator = :initiator AND offer.responder = :responder) OR (offer.initiator = :responder AND offer.responder = :initiator)")
    Optional<NegotiationOffer> findByInitiatorAndResponder(@Param("initiator") User initiator, @Param("responder") User responder);

    @Query("SELECT offer FROM NegotiationOffer offer WHERE offer.initiator = :user OR offer.responder = :user")
    List<NegotiationOffer> findByUser(@Param("user") User user);
}
