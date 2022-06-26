package com.kkp.pelatihanwebservice.internal.security.services;

import com.kkp.pelatihanwebservice.internal.repositories.EmployeeAuthRepository;
import com.kkp.pelatihanwebservice.internal.models.UserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//TODO: deprecated class
@Service
public class EmployeeAuthService implements UserDetailsService {

    @Autowired
    EmployeeAuthRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserApi userApi = employeeRepository.findEmployeeByEmail(email);
        if (userApi == null)
            throw new UsernameNotFoundException("User not found");
        return null ;
    }
}
