package com.kogus.auth.service;

import com.kogus.auth.dto.company.*;
import com.kogus.auth.dto.company.role.*;
import com.kogus.auth.dto.company.user.CompanyUserRequest;
import com.kogus.auth.dto.company.user.CompanyUserResponse;
import com.kogus.auth.entity.*;
import com.kogus.auth.repository.*;
import com.kogus.utils.ReflectionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyAdminService {
    /*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    **** HER ŞEY DOĞRUYMUŞ GİBİ KODLA
    **** EXCEPTION KONTROLLERİ SONRA
    **** ŞUBELİ HALLERİNİ DE YAP!!!!
     */
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final CompanyBranchRepository companyBranchRepository;
    private final PrivilegeRepository privilegeRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final RolePrivilegesRepository rolePrivilegesRepository;

    public CompanyBranchResponse addBranchToCompany(String token, CompanyBranchRequest request) {
        User user = userRepository.findByUsername(jwtService.extractUsername(token)).get();
        request.setCompany(user.getCompany());
        CompanyBranch companyBranch = ReflectionUtils.cast(request, CompanyBranch.class);
        CompanyBranch savedCompanyBranch = companyBranchRepository.save(companyBranch);
        return ReflectionUtils.cast(savedCompanyBranch, CompanyBranchResponse.class);
    }

    public CompanyUserResponse addUserToBranch(String token, CompanyUserRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        String username = jwtService.extractUsername(token);
        User user = userRepository.findByUsername(username).get();
        request.setCompany(user.getCompany());
        User reqUser = ReflectionUtils.cast(request, User.class);
        User savedUser = userRepository.save(reqUser);
        return ReflectionUtils.cast(savedUser, CompanyUserResponse.class);
    }

    public CompanyUserResponse addUserToCompany(String token, CompanyUserRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        String username = jwtService.extractUsername(token);
        User user = userRepository.findByUsername(username).get();
        request.setCompany(user.getCompany());

        User reqUser = ReflectionUtils.cast(request, User.class);
        User savedUser = userRepository.save(reqUser);
        return ReflectionUtils.cast(savedUser, CompanyUserResponse.class);

    }

    public CompanyAddRoleResponse addRoleToCompany(String token, CompanyAddRoleRequest request) {
        User user = userRepository.findByUsername(jwtService.extractUsername(token)).get();
        Role role = Role.builder().name(request.getName()).company(user.getCompany()).build();

        return ReflectionUtils.cast(roleRepository.save(role), CompanyAddRoleResponse.class);
    }

    public CompanyAddPrivilegeToRoleResponse addPrivilegeToRoleCompany(String token, CompanyAddPrivilegeToRoleRequest request) {
        Role role = roleRepository.findById(request.getRoleId()).get();
        Privilege privilege = privilegeRepository.findById(request.getPrivilegeId()).get();
        RolePrivileges rolePrivileges = RolePrivileges.builder().privilege(privilege).role(role).build();
        return ReflectionUtils.cast(rolePrivilegesRepository.save(rolePrivileges), CompanyAddPrivilegeToRoleResponse.class);
    }

    public CompanyAddRoleUserResponse addRoleToUser(String token, CompanyAddRoleUserRequest request) {
        User user = userRepository.findById(request.getUserId()).get();
        List<UserRole> userRoles = user.getRoles();
        request.getRolesId().forEach(id -> {
            Role role = roleRepository.findById(id).get();
            userRoles.add(UserRole.builder().user(user).role(role).build());
        });
        user.setRoles(userRoles);
        return new CompanyAddRoleUserResponse(userRepository.save(user));
    }

    /*public CompanyUserResponse addUserGlobal(String token, CompanyUserRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        String username = jwtService.extractUsername(token);
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("kullanıcı bulunamadı"));
        if (!Objects.isNull(user.getCompanyBranch()))
            throw new NoPermAddUserException("global olarak şirkete kullanıcı ekleme yetkiniz yok");

        User reqUser = ReflectionUtils.cast(request, User.class);
        User savedUser = userRepository.save(reqUser);
        return ReflectionUtils.cast(savedUser, CompanyUserResponse.class);

    }

    public CompanyUserResponse addUserToBranch(String token, CompanyUserRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        String username = jwtService.extractUsername(token);
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("kullanıcı bulunamadı"));
        //daha fazla kontrol eklenebilir
        if (Objects.isNull(request.getCompanyBranch()) || Objects.isNull(request.getCompanyBranch().getId()))
            throw new NoFoundBranchException("lütfen şube belirtin");
        if (user.getCompanyBranch() != null && !Objects.equals(user.getCompanyBranch().getId(), request.getCompany().getId()))
            throw new NoPermAddUserException("şubeye kullanıcı ekleme yetkiniz yok.");

        User reqUser = ReflectionUtils.cast(request, User.class);
        User savedUser = userRepository.save(reqUser);
        return ReflectionUtils.cast(savedUser, CompanyUserResponse.class);

    }

    public CompanyAddRoleUserResponse addRoleToUser(String token, CompanyAddRoleUserRequest request) {
        User user = userRepository.findByUsername(jwtService.extractUsername(token)).orElseThrow(() -> new UsernameNotFoundException("kullanıcı adı bulunamadı"));
        User requestUser = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("kullanıcı bulunamadı"));
        List<UserRole> userRoleList = requestUser.getRoles();
        if (user.getCompanyBranch() != null && !Objects.equals(user.getCompanyBranch().getId(), requestUser.getCompanyBranch().getId()))
            throw new NoPermAddRoleException("bu yetkiyi kendi şuben dışında kimseye veremezsin");
        if (!Objects.equals(user.getCompany().getId(), requestUser.getCompany().getId())) throw new RuntimeException("başka firmayla işin ney");

        if(request.getCompanyBranchId() == null)
            request.getRolesId().forEach((roleId) -> {
                Role role = roleRepository.findByIdAndCompany(roleId, requestUser.getCompany())
                        .orElseThrow(() -> new RuntimeException("role bulunamadı"));
                userRoleList.add(UserRole.builder().role(role).user(requestUser).build());
            });

        requestUser.setRoles(userRoleList);
        userRepository.save(requestUser);
        return new CompanyAddRoleUserResponse(requestUser);

    }*/
}
