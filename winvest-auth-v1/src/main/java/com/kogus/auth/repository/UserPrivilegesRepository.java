package com.kogus.auth.repository;

import com.kogus.auth.entity.UserPrivileges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPrivilegesRepository extends JpaRepository<UserPrivileges, Integer> {
}
