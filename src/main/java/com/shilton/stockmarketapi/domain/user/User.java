package com.shilton.stockmarketapi.domain.user;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class User {
    @Id
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String fullName;
    @Column(unique = true, nullable = false)
    private String email;

    public User() {
    }
}
