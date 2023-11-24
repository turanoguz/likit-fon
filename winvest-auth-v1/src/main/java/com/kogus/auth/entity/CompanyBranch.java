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
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "companies_branches")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CompanyBranch implements Serializable {

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "companyBranch")
    List<User> branchUsers;
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Company company;
    @Column(length = 50)
    private String branchNo;
    @Column(length = 13)
    private String branchCity;
    @Column(length = 20)
    private String branchDistrict;
    @Column(columnDefinition = "TEXT")
    private String branchAddress;
    @Column(length = 13)
    private String branchPhone;
    @Column(length = 30)
    private String branchMail;
    @Temporal(TemporalType.DATE)
    private Date serviceDate;

}
