package com.kogus.auth.dto.company;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kogus.auth.entity.Company;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CompanyBranchResponse {
    private Company company;
    private String branchNo;
    private String branchCity;
    private String branchDistrict;
    private String branchAddress;
    private String branchPhone;
    private String branchMail;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date serviceDate;
}
