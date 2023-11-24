package com.kogus.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Role implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;


    @ManyToOne(cascade = CascadeType.MERGE)
    private Company company;

    @ManyToOne(cascade = CascadeType.MERGE)
    private CompanyBranch companyBranch;

    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(fetch = FetchType.EAGER, targetEntity = RolePrivileges.class, mappedBy = "role")
    private List<RolePrivileges> rolePrivilegesList;
}
