package com.bakigoal.spring;

import com.bakigoal.spring.domain.Role;
import com.bakigoal.spring.domain.MyUser;
import com.bakigoal.spring.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Slf4j
public class SecurityDemoApplication {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public SecurityDemoApplication(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(SecurityDemoApplication.class, args);
    }

    @PostConstruct
    public void init() {
        // test users
        userRepo.save(MyUser.create("admin", passwordEncoder.encode("password"), Role.ROLE_ADMIN));
        userRepo.save(MyUser.create("user", passwordEncoder.encode("password"), Role.ROLE_USER));
        userRepo.save(MyUser.create("hr", passwordEncoder.encode("password"), Role.ROLE_HR));
        userRepo.save(MyUser.create("dev", passwordEncoder.encode("password"), Role.ROLE_DEV));

        log.info("" + userRepo.findAll());
    }

}