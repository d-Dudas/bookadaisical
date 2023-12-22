package com.bookadaisical.repository.specifications;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.bookadaisical.dto.requests.BookSearchFiltersDto;
import com.bookadaisical.enums.ArtisticMovement;
import com.bookadaisical.enums.Condition;
import com.bookadaisical.enums.Genre;
import com.bookadaisical.enums.TargetAudience;
import com.bookadaisical.enums.TradingOption;
import com.bookadaisical.model.Book;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class BookSpecification implements Specification<Book> {

    private BookSearchFiltersDto filters;

    public BookSpecification(BookSearchFiltersDto filters) {
        this.filters = filters;
    }

    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (filters.getGenre() != null && filters.getGenre() != Genre.ALL) {
            predicates.add(criteriaBuilder.isMember(filters.getGenre(), root.get("genres")));
        }

        if (filters.getTargetAudience() != null && filters.getTargetAudience() != TargetAudience.ALL) {
            predicates.add(criteriaBuilder.equal(root.get("targetAudience"), filters.getTargetAudience()));
        }

        if (filters.getArtisticMovement() != null && filters.getArtisticMovement() != ArtisticMovement.ALL) {
            predicates.add(criteriaBuilder.equal(root.get("artisticMovement"), filters.getArtisticMovement()));
        }

        if (filters.getCondition() != null && filters.getCondition() != Condition.ALL) {
            predicates.add(criteriaBuilder.equal(root.get("bookCondition"), filters.getCondition()));
        }

        if (filters.getTradingOption() != null && filters.getTradingOption() != TradingOption.ALL) {
            predicates.add(criteriaBuilder.isMember(filters.getTradingOption(), root.get("tradingOptions")));
        }

        if (filters.getYearOfPublicationNotLessThen() != 0) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("yearOfPublication"), filters.getYearOfPublicationNotLessThen()));
        }
        if (filters.getYearOfPublicationNotBiggerThen() != 0) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("yearOfPublication"), filters.getYearOfPublicationNotBiggerThen()));
        }

        if (filters.getContains() != null && !filters.getContains().isEmpty()) {
            String containsLower = "%" + filters.getContains().toLowerCase() + "%";

            Predicate titlePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), containsLower);
            Predicate descriptionPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), containsLower);
            Predicate authorPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("author")), containsLower);

            predicates.add(criteriaBuilder.or(titlePredicate, descriptionPredicate, authorPredicate));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}