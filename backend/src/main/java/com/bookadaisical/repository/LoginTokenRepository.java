package com.bookadaisical.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.bookadaisical.model.LoginToken;
import com.bookadaisical.model.User;

@Repository
public interface LoginTokenRepository extends JpaRepository<LoginToken, Integer> {

    Optional<LoginToken> findByToken(String token);
    Optional<LoginToken> findByUser(User user);

    @Query("SELECT token FROM LoginToken token WHERE token.token = :token AND token.key = :key")
    Optional<LoginToken> findByTokenAndKey(@Param("token") String token, @Param("key") String key);
}
