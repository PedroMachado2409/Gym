package com.example.demo.Users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);

    Optional<User> findById(Long id);

    @Query("SELECT u FROM users u WHERE u.login = :login")
    User findAuthInfoByLogin(@Param("login") String login);

}
