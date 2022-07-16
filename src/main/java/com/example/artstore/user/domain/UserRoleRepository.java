package com.example.artstore.user.domain;

import com.example.artstore.user.domain.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    @Query(value = "select u from UserRole u where u.name =:role")
    UserRole findByName(Role role);

}
