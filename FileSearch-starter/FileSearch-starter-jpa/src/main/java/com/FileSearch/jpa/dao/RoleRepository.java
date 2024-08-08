package com.FileSearch.jpa.dao;

import com.FileSearch.jpa.models.ERole;
import com.FileSearch.jpa.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
