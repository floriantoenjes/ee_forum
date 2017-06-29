package com.floriantoenjes.ee.forum.ejb.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "FORUM", name = "USR")
public class User {

    @Id
    @SequenceGenerator(
            name = "USR_ID_GENERATOR",
            sequenceName = "SEQ_USR",
            schema = "FORUM",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "USR_ID_GENERATOR"
    )
    private Long id;

    @NotNull
    @Size(min = 3, max = 40)
    @Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+", message = "has to be valid")
    private String email;

    @NotNull
    @Size(min = 6, max = 10)
    private String password;

    @NotNull
    @Size(min = 4, max = 20)
    private String username;

    @ManyToMany
    @JoinTable(name = "USR_ROLE",
            joinColumns = @JoinColumn(name = "USR_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")
    )
    private List<Role> roles;

    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean addRole(Role role) {
        if (roles == null) {
            roles = new ArrayList<>();
        }
        return roles.add(role);
    }
}
