package com.kkp.pelatihanwebservice.internal.security.services;

import com.kkp.pelatihanwebservice.internal.repositories.EmployeeAuthRepository;
import com.kkp.pelatihanwebservice.internal.dto.response.CurrentUserResponse;
import com.kkp.pelatihanwebservice.internal.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeAuthService implements UserDetailsService {

    @Autowired
    EmployeeAuthRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findEmployeeByEmail(email);
        if (employee == null)
            throw new UsernameNotFoundException("User not found");
        return new CurrentUserResponse(employee);
    }
}
