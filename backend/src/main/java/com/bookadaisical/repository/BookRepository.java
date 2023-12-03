package com.bookadaisical.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookadaisical.dto.requests.BookResponseDto;
import com.bookadaisical.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Optional<Book> findById(int id);

    @Query("SELECT b FROM Book b JOIN b.activeBooks ab WHERE EXTRACT(MONTH FROM b.createdOn) = " +
        "EXTRACT(MONTH FROM CURRENT_DATE) AND EXTRACT(YEAR FROM b.createdOn) = EXTRACT(YEAR FROM CURRENT_DATE) ORDER BY b.numViews DESC")
    List<Book> findTopTenBooks(PageRequest pageRequest);

    @Query(nativeQuery = true, value = 
    "SELECT b.id AS id, " +
    "b.title AS title, " +
    "b.author AS author, " +
    "b.num_views AS numViews, " +
    "b.description AS description, " +
    "b.created_on AS createdOn," +
    "gb.genre_name AS genre " +
    "FROM bookadaisical.books b " +
    "JOIN bookadaisical.active_books ab ON b.id = ab.book_id " +
    "JOIN bookadaisical.genres_books gb ON gb.book_id = ab.book_id " +
    "WHERE (gb.genre_name = CAST(:genreName AS bookadaisical.genres) OR CAST(:genreName AS bookadaisical.genres) = 'ALL')")
    List<BookResponseDto> findAllByGenreNativeQuery(@Param("genreName") String genreName);

    /*@Query(nativeQuery = true, value =
    "SELECT DISTINCT " +
    "b.title AS title, " +
    "b.author AS author, " +
    "b.num_views AS numViews, " +
    "b.description AS description, " +
    "b.year_of_publication AS yearOfPublication, " +
    "gb.genre_name AS genre " +
    "FROM bookadaisical.books b " +
    "JOIN bookadaisical.active_books ab ON b.id = ab.book_id " +
    "JOIN bookadaisical.genres_books gb ON gb.book_id = ab.book_id " +
    "WHERE (gb.genre_name = CAST(:genreName AS bookadaisical.genres) OR CAST(:genreName AS bookadaisical.genres) = 'ALL')")
    List<Object[]> findAllByGenreNativeQuery(@Param("genreName") String genreName);
    */


    /*@Query("SELECT b FROM Book b " +
       "JOIN ActiveBook ab ON b.id = ab.activeBook.id " +
       "JOIN GenreBook gb ON gb.book.id = ab.activeBook.id " +
       "WHERE (gb.genre = :genre OR :genre = 'ALL') " +
       "AND (b.targetAudience = :targetAudience OR :targetAudience = 'ALL') " +
       "AND (b.artisticMovement = :artisticMovement OR :artisticMovement = 'ALL') " +
       "AND (b.bookCondition = :bookCondition OR :bookCondition = 'ALL') " +
       "AND b.yearOfPublication < :yearOfPublicationNotBiggerThen " +
       "AND b.yearOfPublication > :yearOfPublicationNotLessThen " +
       "AND (LOWER(b.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
       "OR LOWER(b.author) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
       "OR LOWER(b.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    List<Book> findFilteredBooks(@Param("genre") String genre, // Change the type to String
                                @Param("targetAudience") TargetAudience targetAudience,
                                @Param("artisticMovement") ArtisticMovement artisticMovement, 
                                @Param("bookCondition") String bookCondition,
                                @Param("yearOfPublicationNotBiggerThen") int yearOfPublicationNotBiggerThen,
                                @Param("yearOfPublicationNotLessThen") int yearOfPublicationNotLessThen,
                                @Param("searchTerm") String searchTerm);*/





}
