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
@Table(name = "user_privileges")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserPrivileges implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.MERGE)
    private User user;

    @ManyToOne(targetEntity = Privilege.class, cascade = CascadeType.MERGE)
    private Privilege privilege;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Company company;

    @ManyToOne(cascade = CascadeType.MERGE)
    private CompanyBranch companyBranch;

}
