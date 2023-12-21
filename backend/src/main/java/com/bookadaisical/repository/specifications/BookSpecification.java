package com.bookadaisical.repository.specifications;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.bookadaisical.dto.requests.BookSearchFiltersDto;
import com.bookadaisical.model.Book;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

// import javax.persistence.criteria.*;

public class BookSpecification implements Specification<Book> {

    private BookSearchFiltersDto filters;

    public BookSpecification(BookSearchFiltersDto filters) {
        this.filters = filters;
    }

    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (filters.getGenre() != null) {
            predicates.add(criteriaBuilder.equal(root.get("genre"), filters.getGenre()));
        }

        // Add similar checks for other fields in filters
        // Example for range (yearOfPublication)
        if (filters.getYearOfPublicationNotLessThen() != 0) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("yearOfPublication"), filters.getYearOfPublicationNotLessThen()));
        }
        if (filters.getYearOfPublicationNotBiggerThen() != 0) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("yearOfPublication"), filters.getYearOfPublicationNotBiggerThen()));
        }

        // Add similar predicates for other fields like targetAudience, artisticMovement, etc.

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}