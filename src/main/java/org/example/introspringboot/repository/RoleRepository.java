package org.example.introspringboot.repository;

import org.example.introspringboot.entity.Professor;
import org.example.introspringboot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
