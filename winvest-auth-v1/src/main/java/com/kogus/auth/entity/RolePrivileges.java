package com.kogus.auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role_privileges")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RolePrivileges implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(targetEntity = Role.class, cascade = CascadeType.MERGE)
    private Role role;

    @ManyToOne(targetEntity = Privilege.class, cascade = CascadeType.MERGE)
    private Privilege privilege;

}
