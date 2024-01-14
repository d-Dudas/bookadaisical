package com.bookadaisical.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookadaisical.dto.requests.NegotiatingUsersDto;
import com.bookadaisical.dto.requests.NegotiationOfferDto;
import com.bookadaisical.dto.responses.ExistingNegotiationOfferDto;
import com.bookadaisical.enums.NegotiationStatus;
import com.bookadaisical.exceptions.BookNotFoundException;
import com.bookadaisical.exceptions.ClosedNegotiationException;
import com.bookadaisical.exceptions.NegotiationCantBeAcceptedBySenderException;
import com.bookadaisical.exceptions.NegotiationNotFoundException;
import com.bookadaisical.exceptions.UserNotFoundException;
import com.bookadaisical.model.Book;
import com.bookadaisical.model.NegotiationOffer;
import com.bookadaisical.model.User;
import com.bookadaisical.repository.BookRepository;
import com.bookadaisical.repository.NegotiationOfferRepository;
import com.bookadaisical.repository.UserRepository;
import com.bookadaisical.service.interfaces.INegotiationService;

@Service
public class NegotiationService implements INegotiationService {
    @Autowired
    private NegotiationOfferRepository negotiationOfferRepository;

    @Autowired 
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void sendOffer(NegotiationOfferDto negotiationOfferDto) throws Exception
    {
        Optional<User> initiator = userRepository.findByUsername(negotiationOfferDto.getInitiatorUsername());
        if(initiator.isEmpty()) throw new UserNotFoundException();

        Optional<User> responder = userRepository.findByUsername(negotiationOfferDto.getResponderUsername());
        if(responder.isEmpty()) throw new UserNotFoundException();

        Set<Book> initiatorSelectedBooks = new HashSet<>();
        for(UUID bookId : negotiationOfferDto.getInitiatorSelectedBooks())
        {
            Optional<Book> book = bookRepository.findById(bookId);
            if(book.isEmpty()) throw new BookNotFoundException();

            initiatorSelectedBooks.add(book.get());
        }

        Set<Book> responderSelectedBooks = new HashSet<>();
        for(UUID bookId : negotiationOfferDto.getResponderSelectedBooks())
        {
            Optional<Book> book = bookRepository.findById(bookId);
            if(book.isEmpty()) throw new BookNotFoundException();

            responderSelectedBooks.add(book.get());
        }

        NegotiationOffer negotiationOffer;
        Optional<NegotiationOffer> existingNegotiationOffer = negotiationOfferRepository.findByInitiatorAndResponder(initiator.get(), responder.get());
        if(existingNegotiationOffer.isPresent())
        {
            negotiationOffer = existingNegotiationOffer.get();
            negotiationOffer.setInitiator(initiator.get());
            negotiationOffer.setResponder(responder.get());
            negotiationOffer.setInitiatorSelectedBooks(initiatorSelectedBooks);
            negotiationOffer.setResponderSelectedBooks(responderSelectedBooks);
            negotiationOffer.setNegotiationStatus(NegotiationStatus.ONGOING);
            negotiationOffer.setLastUpdated(LocalDateTime.now());

        } else {
            negotiationOffer = new NegotiationOffer(initiator.get(), responder.get(), initiatorSelectedBooks, responderSelectedBooks);
        }

        negotiationOfferRepository.save(negotiationOffer);
    }

    @Override
    public ExistingNegotiationOfferDto getExistingNegotiation(NegotiatingUsersDto negotiatingUsersDto) throws Exception
    {
        Optional<User> initiator = userRepository.findByUsername(negotiatingUsersDto.getInitiatorUsername());
        if(initiator.isEmpty()) throw new UserNotFoundException();

        Optional<User> responder = userRepository.findByUsername(negotiatingUsersDto.getResponderUsername());
        if(responder.isEmpty()) throw new UserNotFoundException();

        Optional<NegotiationOffer> negotiationOffer = negotiationOfferRepository.findByInitiatorAndResponder(initiator.get(), responder.get());
        if(negotiationOffer.isEmpty()) throw new NegotiationNotFoundException();

        if(negotiationOffer.get().getNegotiationStatus().compareTo(NegotiationStatus.CANCELED) == 0) throw new NegotiationNotFoundException();

        return modelMapper.map(negotiationOffer.get(), ExistingNegotiationOfferDto.class);
    }

    @Override
    public void acceptOffer(NegotiatingUsersDto negotiatingUsersDto) throws Exception
    {
        Optional<User> initiator = userRepository.findByUsername(negotiatingUsersDto.getInitiatorUsername());
        if(initiator.isEmpty()) throw new UserNotFoundException();

        Optional<User> responder = userRepository.findByUsername(negotiatingUsersDto.getResponderUsername());
        if(responder.isEmpty()) throw new UserNotFoundException();

        Optional<NegotiationOffer> negotiationOffer = negotiationOfferRepository.findByInitiatorAndResponder(initiator.get(), responder.get());
        if(negotiationOffer.isEmpty()) throw new NegotiationNotFoundException();

        if(negotiationOffer.get().getNegotiationStatus().compareTo(NegotiationStatus.ONGOING) != 0) throw new ClosedNegotiationException();

        if(negotiationOffer.get().getInitiator().equals(initiator.get())) throw new NegotiationCantBeAcceptedBySenderException();

        negotiationOffer.get().setNegotiationStatus(NegotiationStatus.ACCEPTED);
        negotiationOfferRepository.save(negotiationOffer.get());
    }

    @Override
    public void cancelNegotiation(NegotiatingUsersDto negotiatingUsersDto) throws Exception
    {
        Optional<User> initiator = userRepository.findByUsername(negotiatingUsersDto.getInitiatorUsername());
        if(initiator.isEmpty()) throw new UserNotFoundException();

        Optional<User> responder = userRepository.findByUsername(negotiatingUsersDto.getResponderUsername());
        if(responder.isEmpty()) throw new UserNotFoundException();

        Optional<NegotiationOffer> negotiationOffer = negotiationOfferRepository.findByInitiatorAndResponder(initiator.get(), responder.get());
        if(negotiationOffer.isEmpty()) throw new NegotiationNotFoundException();

        if(negotiationOffer.get().getNegotiationStatus().compareTo(NegotiationStatus.CANCELED) == 0) throw new ClosedNegotiationException();

        negotiationOffer.get().setNegotiationStatus(NegotiationStatus.CANCELED);
        negotiationOfferRepository.save(negotiationOffer.get());
    }
}
