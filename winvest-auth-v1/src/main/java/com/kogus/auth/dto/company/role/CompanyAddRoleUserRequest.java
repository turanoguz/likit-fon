package com.kogus.auth.dto.company.role;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompanyAddRoleUserRequest {

    private Long userId;
    private List<Integer> rolesId;
    private Integer companyBranchId;

}
