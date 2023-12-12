package com.ism.repository;

import com.ism.model.Role;
import com.ism.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository  extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
