package com.kogus.auth.dto.company.role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyAddPrivilegeToRoleRequest {
    Integer roleId;
    Integer privilegeId;
}
