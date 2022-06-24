package ru.kata.spring.boot_security.demo.model;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test()
    void getAuthorities() {
        User user = new User();
        Collection<Role> role = new ArrayList<>();
        Role userRole = new Role();
        userRole.setRole("ROLE_ADMIN");
        role.add(userRole);
        user.setUserRoles(role);
        assertArrayEquals(role.toArray(new Role[0]), user.getUserRoles().toArray());
    }

}
