package com.kogus.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "companies")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Company implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    @NotNull
    @Column(length = 25)
    private String companyName;
    @NotNull
    @Column(length = 50)
    private String recordNo;
    @Column(length = 13)
    private String headQuartersPhone;
    @Column(columnDefinition = "TEXT")
    private String headQuartersAddress;
    @Column(length = 13)
    private String companyPhone;
    @Temporal(TemporalType.DATE)
    private Date foundationDate;

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "company", cascade = CascadeType.MERGE)
    private List<CompanyBranch> companyBranches;
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "company", cascade = CascadeType.MERGE)
    private List<User> companyUsers;

}
