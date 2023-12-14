package com.bookadaisical.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookadaisical.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findById(UUID id);

    @Query("SELECT u FROM User u WHERE (u.username = :usernameOrEmail OR u.email = :usernameOrEmail) AND u.password = :password")
    Optional<User> findByUsernameOrEmailAndPassword(@Param("usernameOrEmail") String usernameOrEmail, @Param("password") String password);

    @Modifying
    @Query("UPDATE User SET username = :newUsername WHERE id = :userId")
    void updateUsername(@Param("userId") UUID userId, @Param("newUsername") String newUsername);

    @Modifying
    @Query("UPDATE User SET email = :newEmail WHERE id = :userId")
    void updateEmail(@Param("userId") UUID userId, @Param("newEmail") String newEmail);

    @Modifying
    @Query("UPDATE User SET password = :newPassword WHERE id = :userId")
    void updatePassword(@Param("userId") UUID userId, @Param("newPassword") String newPassword);
}
