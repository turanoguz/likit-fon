package com.kogus.auth.repository;

import com.kogus.auth.entity.User;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    @QueryHints(value = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<User> findAll();

    @QueryHints(value = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    Optional<User> findByEmail(String email);

    @QueryHints(value = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    Optional<User> findByUsername(String username);
}
