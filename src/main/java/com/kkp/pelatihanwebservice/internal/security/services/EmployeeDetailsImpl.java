package com.kkp.pelatihanwebservice.internal.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kkp.pelatihanwebservice.internal.models.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

public class EmployeeDetailsImpl implements UserDetails {
    private static final Long serialVersionUID = 1L;

    private Long id;
    private String fullname;
    private String email;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime emailVerifiedAt;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public EmployeeDetailsImpl(Long id, String fullname, String email, String password,
                               LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime emailVerifiedAt) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.emailVerifiedAt = emailVerifiedAt;
        this.password = password;
    }

    public static EmployeeDetailsImpl build(Employee employee) {

        return new EmployeeDetailsImpl(employee.getId(), employee.getFullname(), employee.getEmail(),
                employee.getPassword(), employee.getCreatedAt(),
                employee.getUpdatedAt(), employee.getEmailVerifiedAt());
    }

    public Long getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
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
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        EmployeeDetailsImpl employeeDetails = (EmployeeDetailsImpl) obj;
        return Objects.equals(id, employeeDetails.id);
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getEmailVerifiedAt() {
        return emailVerifiedAt;
    }

    public void setEmailVerifiedAt(LocalDateTime emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
    }
}
