package com.kogus.auth.repository;

import com.kogus.auth.entity.Company;
import com.kogus.auth.entity.CompanyBranch;
import com.kogus.auth.entity.Role;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @QueryHints(value = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    Optional<Role> findByNameAndCompany(String roleName, Company company);

    @QueryHints(value = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    Optional<Role> findByNameAndCompanyAndCompanyBranch(String roleName, Company company, CompanyBranch companyBranch);

    @QueryHints(value = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    Optional<Role> findByIdAndCompany(Integer roleId, Company company);

    @QueryHints(value = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    Optional<Role> findByIdAndCompanyAndCompanyBranch(Integer roleId, Company company, CompanyBranch companyBranch);


    @QueryHints(value = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    boolean existsByName(String name);
}
