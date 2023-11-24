package com.kogus.auth.dto.company.role;

import com.kogus.auth.entity.Company;
import com.kogus.auth.entity.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyAddRoleResponse {

    private String name;

}
