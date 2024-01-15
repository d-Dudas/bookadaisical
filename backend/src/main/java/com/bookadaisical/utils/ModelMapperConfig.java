package com.bookadaisical.utils;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bookadaisical.dto.requests.NegotiationOfferDto;
import com.bookadaisical.dto.responses.ExistingNegotiationOfferDto;
import com.bookadaisical.dto.responses.UserDto;
import com.bookadaisical.model.Book;
import com.bookadaisical.model.NegotiationOffer;
import com.bookadaisical.model.User;

@Configuration
public class ModelMapperConfig {
    
    @Bean
    public ModelMapper modelMapper()
    {
        ModelMapper modelMapper = new ModelMapper();

        Converter<Set<Book>, List<UUID>> bookSetToUuidListConverter = new Converter<Set<Book>, List<UUID>>() {
            @Override
            public List<UUID> convert(MappingContext<Set<Book>, List<UUID>> context) {
                return context.getSource().stream()
                            .map(Book::getId)
                            .collect(Collectors.toList());
            }
        };

        modelMapper.addMappings(new PropertyMap<NegotiationOffer, ExistingNegotiationOfferDto>() {
            @Override
            protected void configure()
            {
                using(bookSetToUuidListConverter).map(source.getInitiatorSelectedBooks()).setInitiatorSelectedBooks(null);
                using(bookSetToUuidListConverter).map(source.getResponderSelectedBooks()).setResponderSelectedBooks(null);
                
                map().setInitiatorUsername(source.getInitiator().getUsername());
                map().setResponderUsername(source.getResponder().getUsername());
                map().setStatus(source.getNegotiationStatus());
            }
        });

        modelMapper.addMappings(new PropertyMap<NegotiationOffer, NegotiationOfferDto>() {
            @Override
            protected void configure()
            {
                map(source.getInitiator().getUsername(), destination.getInitiatorUsername());
                map(source.getResponder().getUsername(), destination.getResponderUsername());

                using(bookSetToUuidListConverter).map(source.getInitiatorSelectedBooks()).setInitiatorSelectedBooks(null);
                using(bookSetToUuidListConverter).map(source.getResponderSelectedBooks()).setResponderSelectedBooks(null);
            }
        });

        modelMapper.addMappings(new PropertyMap<User, UserDto>() {
            @Override
            protected void configure()
            {
                map(source.getUsername(), destination.getUsername());
                map(source.getEmail(), destination.getEmail());
                map(source.getCurrentPoints(), destination.getCurrentPoints());
                map(source.getTotalPoints(), destination.getTotalPoints());
                map(source.getSpecialCurrency(), destination.getSpecialCurrency());
                map(source.getImage().getImageData(), destination.getProfilePicture());
            }
        });


        return modelMapper;
    }
}
