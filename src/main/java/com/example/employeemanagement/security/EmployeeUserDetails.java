package com.example.employeemanagement.security;

import com.example.employeemanagement.entity.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class EmployeeUserDetails implements UserDetails {

    private final Employee employee;

    public EmployeeUserDetails(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String getUsername() {
        return employee.getUsername();
    }

    @Override
    public String getPassword() {
        return employee.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // For now, everyone is a USER
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
