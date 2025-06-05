package com.shilton.stockmarketapi.repository;


import com.shilton.stockmarketapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository<F> extends JpaRepository<User, String> {
    Optional<User> findUserByUsername(String username);
}
