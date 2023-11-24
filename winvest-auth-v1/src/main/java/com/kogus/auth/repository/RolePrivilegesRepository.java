package com.kogus.auth.repository;

import com.kogus.auth.entity.RolePrivileges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePrivilegesRepository extends JpaRepository<RolePrivileges, Integer> {
}
