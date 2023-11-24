package com.kogus.auth.dto.company.role;

import com.kogus.auth.entity.Privilege;
import com.kogus.auth.entity.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyAddPrivilegeToRoleResponse {

    private Integer id;
    private Role role;
    private Privilege privilege;

}
