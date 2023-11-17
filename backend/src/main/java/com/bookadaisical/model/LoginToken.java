package com.bookadaisical.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "login_tokens", schema = "bookadaisical")
public class LoginToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private String key;

    @Column(name = "last_validated_on", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp lastValidatedOn;

    public LoginToken(User user, String token, String key) {
        this.user = user;
        this.token = token;
        this.key = key;
        this.lastValidatedOn = new Timestamp(System.currentTimeMillis());
    }
}
