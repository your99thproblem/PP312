package ru.kata.spring.boot_security.demo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "UserRole")
@Table(name = "user_role")
public class UserRole {
    @EmbeddedId
    private UserRoleId id;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roleId")
    private Role role;

    public UserRole(User user, Role role) {

        this.user = user;
        this.role = role;
        this.id = new UserRoleId(user.getId(), role.getId());

    }

    public UserRole() {

    }

    public UserRoleId getId() {
        return id;
    }

    public void setId(UserRoleId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return Objects.equals(user, userRole.user) && Objects.equals(role, userRole.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, role);
    }
}
