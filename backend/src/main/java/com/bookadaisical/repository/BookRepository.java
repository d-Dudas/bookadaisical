package com.bookadaisical.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookadaisical.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    
    @Query("SELECT b FROM Book b JOIN b.activeBooks ab WHERE EXTRACT(MONTH FROM b.createdOn) = " +
        "EXTRACT(MONTH FROM CURRENT_DATE) AND EXTRACT(YEAR FROM b.createdOn) = EXTRACT(YEAR FROM CURRENT_DATE) ORDER BY b.numViews DESC")
    List<Book> findTopTenBooks(PageRequest pageRequest);


    

}
