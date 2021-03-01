package be.uantwerpen.fti.se.tutorial.Model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Entity
public class User extends AbstractPersistable<Long> implements UserDetails {
    private static String DEFAULT_USERNAME = "default_username";
    private static String DEFAULT_PASSWORD = "default_password";

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column
    private String email;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String uaNumber;

    @Column
    private String telephone;
    @Column
    private String location;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)}
    )
    private Set<Role> roles;

    @Column(name = "date_created", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @Column(name = "updated_on")
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    public User() {
        this(User.DEFAULT_USERNAME, User.DEFAULT_PASSWORD);
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.roles = new TreeSet<>();
    }

    public User(String username,
                String password,
                String email,
                String firstName,
                String lastName,
                String uaNumber,
                String telephone,
                String location,
                Set<Role> roles,
                LocalDateTime dateCreated,
                LocalDateTime updateDateTime) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.uaNumber = uaNumber;
        this.telephone = telephone;
        this.location = location;
        this.roles = roles;
        this.dateCreated = dateCreated;
        this.updateDateTime = updateDateTime;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(Role::getPrivileges)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    // ***************
    // Getters
    // ***************

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUaNumber() {
        return uaNumber;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getLocation() {
        return location;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    // ***************
    // Setters
    // ***************

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUaNumber(String uaNumber) {
        this.uaNumber = uaNumber;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

}
