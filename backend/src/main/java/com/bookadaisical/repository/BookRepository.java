package com.bookadaisical.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookadaisical.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    
    @Query("SELECT b FROM Book b, ActiveBook ab " +
           "WHERE b.id = ab.bookId " +
           "AND EXTRACT(MONTH FROM b.createdOn) = EXTRACT(MONTH FROM CURRENT_DATE) " +
           "AND EXTRACT(YEAR FROM b.createdOn) = EXTRACT(YEAR FROM CURRENT_DATE) " +
           "ORDER BY b.numViews DESC")
    List<Book> findTopTenBooks();
    

}
