package com.kogus.auth.dto.company.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kogus.auth.entity.Company;
import com.kogus.auth.entity.CompanyBranch;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CompanyUserResponse {

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String recordNo;
    private Company company;
    private CompanyBranch companyBranch;
    private String phone;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date startWorkDate;

}
