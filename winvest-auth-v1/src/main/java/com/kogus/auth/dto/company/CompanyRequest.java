package com.kogus.auth.dto.company;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CompanyRequest {
    private String companyName;
    private String recordNo;
    private String headQuartersPhone;
    private String headQuartersAddress;
    private String companyPhone;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date foundationDate;
}
