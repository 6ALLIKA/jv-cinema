package com.dev.cinema.controller;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.extern.log4j.Log4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

@Log4j
@Controller
public class InjectDataController {
    private final RoleService roleService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public InjectDataController(RoleService roleService, UserService userService,
                                PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        injectRoles();
        injectAdmin();
        injectUser();
    }

    private void injectRoles() {
        Role userRole = new Role();
        userRole.setName(Role.RoleName.USER);
        roleService.add(userRole);
        Role adminRole = new Role();
        adminRole.setName(Role.RoleName.ADMIN);
        roleService.add(adminRole);
        log.info("USER and ADMIN roles were injected to DB");
    }

    private void injectAdmin() {
        User admin = new User();
        admin.setEmail("admin@gmail.com");
        admin.setPassword(passwordEncoder.encode("1234"));
        Role adminRole = roleService.getRoleByName("ADMIN");
        admin.setRoles(Set.of(adminRole));
        userService.add(admin);
        log.info("A user with ADMIN role was injected to DB");
    }

    private void injectUser() {
        User user = new User();
        user.setEmail("user@gmail.com");
        user.setPassword(passwordEncoder.encode("1234"));
        Role userRole = roleService.getRoleByName("USER");
        user.setRoles(Set.of(userRole));
        userService.add(user);
        log.info("A user with USER role was injected to DB");
    }
}
