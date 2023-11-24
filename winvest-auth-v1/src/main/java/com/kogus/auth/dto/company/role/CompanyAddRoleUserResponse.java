package com.kogus.auth.dto.company.role;

import com.kogus.auth.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CompanyAddRoleUserResponse {
    private User user;
}
