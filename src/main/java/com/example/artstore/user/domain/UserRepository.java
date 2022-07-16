package com.example.artstore.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "select u from User u join fetch u.roleEntities where u.username =:username")
    Optional<User> findUserWithRolesByUsername(String username);

    User findByUsername(String username);

    User findByEmail(String email);
}
