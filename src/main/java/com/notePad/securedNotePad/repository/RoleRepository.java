package com.notePad.securedNotePad.repository;

import com.notePad.securedNotePad.entity.AppRole;
import com.notePad.securedNotePad.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByEmail(AppRole appRole);
}
