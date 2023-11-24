package com.kogus.winvestapplication;

import com.kogus.auth.dto.RegisterRequest;
import com.kogus.auth.entity.*;
import com.kogus.auth.repository.*;
import com.kogus.auth.service.AuthenticationService;
import com.kogus.events.EventManager;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.embedded.tomcat.TomcatProtocolHandlerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executors;


@SpringBootApplication(scanBasePackages = {"com.kogus"})
@ComponentScan("com.kogus")
@EnableJpaRepositories(basePackages = "com.kogus")
@EntityScan("com.kogus.*")
@RequiredArgsConstructor
@EnableScheduling
public class WinvestApplication {

    final AuthenticationService service;
    final UserRepository userRepository;
    final PrivilegeRepository privilegeRepository;
    final RoleRepository roleRepository;
    final RolePrivilegesRepository rolePrivilegesRepository;
    final UserRolesRepository userRolesRepository;
    final UserPrivilegesRepository userPrivilegesRepository;
    final CompanyRepository companyRepository;

    public static void main(String[] args) {
        SpringApplication.run(WinvestApplication.class, args);
        EventManager.registerAllListenerClass();
    }

    //@EventListener(ApplicationReadyEvent.class)
    public void dbSave() {
        Company company = Company.builder().companyName("WINVEST").recordNo("1").build();
        companyRepository.save(company);

        RegisterRequest winvestAdmin = RegisterRequest.builder().username("winvest").password("admin").recordNo("1").build();
        service.register(winvestAdmin);
        Role role = Role.builder().id(1).company(company).name("WINVEST-ADMIN").build();
        roleRepository.save(role);

        UserRole userRole = UserRole.builder().user(User.builder().id(1L).build()).role(role).build();
        userRolesRepository.save(userRole);
    }
    @Bean
    public AsyncTaskExecutor applicationTaskExecutor() {
        return new TaskExecutorAdapter(Executors.newVirtualThreadPerTaskExecutor());
    }
    @Bean
    public TomcatProtocolHandlerCustomizer<?> protocolHandlerVirtualThreadExecutorCustomizer() {
        return protocolHandler -> protocolHandler.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}