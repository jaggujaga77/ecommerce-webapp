package com.abhicodes.datapoem.produser.repository;

import com.abhicodes.datapoem.produser.entity.Role;
import com.abhicodes.datapoem.produser.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
