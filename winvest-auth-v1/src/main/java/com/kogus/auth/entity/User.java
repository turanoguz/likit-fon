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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.function.Predicate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @Column(length = 20)
    private String username;
    @NotNull
    private String password;
    @Column(length = 20)
    private String firstname;
    @Column(length = 20)
    private String lastname;
    @Column(length = 30)
    private String email;
    @NotNull
    @Column(length = 50)
    private String recordNo;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Company company;
    @ManyToOne(cascade = CascadeType.MERGE)
    private CompanyBranch companyBranch;
    @Column(length = 13)
    private String phone;
    @Temporal(TemporalType.DATE)
    private Date startWorkDate;

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, targetEntity = UserRole.class, mappedBy = "user", cascade = CascadeType.MERGE)
    private List<UserRole> roles;

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, targetEntity = UserPrivileges.class, mappedBy = "user", cascade = CascadeType.MERGE)
    private List<UserPrivileges> privileges;
    
    @OneToOne(fetch = FetchType.EAGER, targetEntity = Token.class, mappedBy = "user", cascade = CascadeType.MERGE)
    private Token token;	

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<SimpleGrantedAuthority> simpleGrantedAuthorityList = new HashSet<>();
        //ortak bir sınıf ile predicate 1'e düşürebilir.
        Predicate<UserRole> userRolePredicate = (r) -> Objects.isNull(r.getRole().getCompanyBranch()) ||
                (Objects.equals(r.getRole().getCompany().getId(), company.getId()) && Objects.equals(r.getRole().getCompanyBranch().getId(), companyBranch.getId()));

        Predicate<UserPrivileges> userPrivilegesPredicate = (r) -> Objects.isNull(r.getCompanyBranch()) ||
                (Objects.equals(r.getCompany().getId(), company.getId()) && Objects.equals(r.getCompanyBranch().getId(), companyBranch.getId()));


        if (roles == null) return simpleGrantedAuthorityList;
        var a = roles;
        var b = privileges;
        roles.stream()
                .filter(userRolePredicate)
                .forEach((r) -> {
                    r.getRole()
                            .getRolePrivilegesList().forEach((rolePrivileges -> simpleGrantedAuthorityList.add(new SimpleGrantedAuthority(rolePrivileges.getPrivilege().getName()))));
                    simpleGrantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_" + r.getRole().getName()));

                });

        privileges.stream()
                .filter(userPrivilegesPredicate)
                .forEach((up -> simpleGrantedAuthorityList.add(new SimpleGrantedAuthority(up.getPrivilege().getName()))));

        return simpleGrantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
