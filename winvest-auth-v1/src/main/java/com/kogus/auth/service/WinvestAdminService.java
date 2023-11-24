package com.kogus.auth.service;

import com.kogus.auth.dto.company.*;
import com.kogus.auth.dto.company.user.CompanyUserRequest;
import com.kogus.auth.dto.company.user.CompanyUserResponse;
import com.kogus.auth.entity.*;
import com.kogus.auth.repository.*;
import com.kogus.utils.ReflectionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WinvestAdminService {

    private final CompanyRepository companyRepository;
    private final CompanyBranchRepository companyBranchRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRolesRepository userRolesRepository;
    private final PasswordEncoder passwordEncoder;

    public CompanyResponse addCompany(CompanyRequest companyRequest) {
        Company company = ReflectionUtils.cast(companyRequest, Company.class);
        Company companySaved = companyRepository.save(company);
        return ReflectionUtils.cast(companySaved, CompanyResponse.class);
    }

    public CompanyBranchResponse addBranchToCompany(CompanyBranchRequest companyBranchRequest) {
        CompanyBranch companyBranch = ReflectionUtils.cast(companyBranchRequest, CompanyBranch.class);
        CompanyBranch companyBranchSaved = companyBranchRepository.save(companyBranch);
        return ReflectionUtils.cast(companyBranchSaved, CompanyBranchResponse.class);
    }

    public CompanyUserResponse addAdminUserToCompany(CompanyUserRequest companyUserRequest) {
        companyUserRequest.setPassword(passwordEncoder.encode(companyUserRequest.getPassword()));
        User user = ReflectionUtils.cast(companyUserRequest, User.class);
        User savedUser = userRepository.save(user);
        Company company = companyRepository.findById(companyUserRequest.getCompany().getId()).get();
        Role role = roleRepository.findByNameAndCompany("ADMIN", company).orElseGet(() -> roleRepository.save(Role.builder().company(company).name("ADMIN").build()));
        userRolesRepository.save(UserRole.builder().user(user).role(role).build());
        return ReflectionUtils.cast(savedUser, CompanyUserResponse.class);
    }

}
