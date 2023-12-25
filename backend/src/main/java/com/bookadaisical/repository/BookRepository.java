package com.bookadaisical.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookadaisical.enums.Genre;
import com.bookadaisical.model.Book;
import com.bookadaisical.utils.GenreCount;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID>, JpaSpecificationExecutor<Book> {
   Optional<Book> findById(UUID id);

   @Query("SELECT b FROM Book b WHERE b.createdOn >= :startDate ORDER BY b.numViews DESC")
   List<Book> findBooksByUploadDateAndViews(@Param("startDate") LocalDateTime startDate, Pageable pageable);

   @Query(value = "SELECT * FROM bookadaisical.books", nativeQuery = true)
   List<Book> findAllByGenreNativeQuery();

   @Query("SELECT b FROM Book b WHERE b.uploader.username = :username")
   List<Book> findAllBooksByUploaderUsername(@Param("username") String username);

   List<Book> findTop10ByIsActiveOrderByCreatedOnDesc(boolean isActive, Pageable pageable);

   @Query("SELECT new com.bookadaisical.utils.GenreCount(b.genres, SUM(b.numViews)) " +
           "FROM Book b JOIN b.genres genres " +
           "GROUP BY genres " +
           "ORDER BY SUM(b.numViews) DESC")
   List<GenreCount> findTop4GenresByPopularity(Pageable pageable);

   @Query("SELECT b FROM Book b JOIN b.genres g WHERE b.isActive = true AND g = :genre " +
           "ORDER BY b.numViews DESC LIMIT 1")
   Optional<Book> findMostPopularActiveBookByGenre(Genre genre);
}
