package com.kogus.auth.controller;

import com.kogus.auth.dto.company.*;
import com.kogus.auth.dto.company.user.CompanyUserRequest;
import com.kogus.auth.dto.company.user.CompanyUserResponse;
import com.kogus.auth.service.WinvestAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('WINVEST-ADMIN')")
public class WinvestAdminController {

    private final WinvestAdminService winvestAdminService;

    @PostMapping("/add-company")
    public ResponseEntity<CompanyResponse> addCompany(@RequestBody CompanyRequest request) {
        return ResponseEntity.ok(winvestAdminService.addCompany(request));
    }

    @PostMapping("/add-branch")
    public ResponseEntity<CompanyBranchResponse> addBranchToCompany(@RequestBody CompanyBranchRequest request) {
        return ResponseEntity.ok(winvestAdminService.addBranchToCompany(request));
    }

    @PostMapping("/add-admin-to-company")
    public ResponseEntity<CompanyUserResponse> addAdminUserToCompany(@RequestBody CompanyUserRequest request) {
        return ResponseEntity.ok(winvestAdminService.addAdminUserToCompany(request));
    }

}
