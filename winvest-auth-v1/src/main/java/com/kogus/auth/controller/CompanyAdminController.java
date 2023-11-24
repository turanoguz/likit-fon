package com.kogus.auth.controller;

import com.kogus.auth.dto.company.*;
import com.kogus.auth.dto.company.role.*;
import com.kogus.auth.dto.company.user.CompanyUserRequest;
import com.kogus.auth.dto.company.user.CompanyUserResponse;
import com.kogus.auth.service.CompanyAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/company")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class CompanyAdminController {

    private final CompanyAdminService companyAdminService;
    @PostMapping("/add-branch")
    public ResponseEntity<CompanyBranchResponse> addBranchToCompany(@RequestHeader("Authorization") String token, @RequestBody CompanyBranchRequest request) {
        return ResponseEntity.ok(companyAdminService.addBranchToCompany(token, request));
    }

    @PostMapping("/add-user-branch")
    public ResponseEntity<CompanyUserResponse> addUserToBranch(@RequestHeader("Authorization") String token, @RequestBody CompanyUserRequest request) {
        return ResponseEntity.ok(companyAdminService.addUserToBranch(token, request));
    }

    @PostMapping("/add-user-global")
    public ResponseEntity<CompanyUserResponse> addUserGlobal(@RequestHeader("Authorization") String token, @RequestBody CompanyUserRequest request) {
        return ResponseEntity.ok(companyAdminService.addUserToCompany(token, request));
    }

    @PostMapping("/add-role-global")
    public ResponseEntity<CompanyAddRoleResponse> addRoleToCompany(@RequestHeader("Authorization") String token, @RequestBody CompanyAddRoleRequest request) {
        return ResponseEntity.ok(companyAdminService.addRoleToCompany(token, request));
    }
    @PostMapping("/add-privilege-role-global")
    public ResponseEntity<CompanyAddPrivilegeToRoleResponse> addPrivilegeToRoleCompany(@RequestHeader("Authorization") String token, @RequestBody CompanyAddPrivilegeToRoleRequest request) {
        return ResponseEntity.ok(companyAdminService.addPrivilegeToRoleCompany(token, request));
    }

    @PostMapping("/add-role-user")
    public ResponseEntity<CompanyAddRoleUserResponse> addRoleToUser(@RequestHeader("Authorization") String token, @RequestBody CompanyAddRoleUserRequest request) {
        return ResponseEntity.ok(companyAdminService.addRoleToUser(token, request));
    }

    /*@PostMapping("/add-user-global")
    public ResponseEntity<CompanyUserResponse> addUserGlobal(@RequestHeader("Authorization") String token, @RequestBody CompanyUserRequest request) {
        return ResponseEntity.ok(companyAdminService.addUserGlobal(token, request));
    }

    @PostMapping("/add-user-to-branch")
    public ResponseEntity<CompanyUserResponse> addUserToBranch(@RequestHeader("Authorization") String token, @RequestBody CompanyUserRequest request) {
        return ResponseEntity.ok(companyAdminService.addUserToBranch(token, request));
    }

    @PostMapping("/add-role-to-user")
    public ResponseEntity<CompanyAddRoleUserResponse> addRoleToUser(@RequestHeader("Authorization") String token, @RequestBody CompanyAddRoleUserRequest request) {
        return ResponseEntity.ok(companyAdminService.addRoleToUser(token, request));
    }*/


}
