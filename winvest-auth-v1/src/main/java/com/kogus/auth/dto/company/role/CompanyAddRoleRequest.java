package com.kogus.auth.dto.company.role;

import com.kogus.auth.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompanyAddRoleRequest {

    private String name;
    private Integer companyBranchId;

}
