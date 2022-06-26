package com.kkp.pelatihanwebservice.internal.security.services;

import com.kkp.pelatihanwebservice.internal.repositories.UserApiRepository;
import com.kkp.pelatihanwebservice.internal.models.UserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UserApiDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserApiRepository userApiRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserApi userApi = userApiRepository.findEmployeeByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        userApi.setEmailVerifiedAt(LocalDateTime.now());
        userApiRepository.save(userApi);

        return UserApiDetailsImpl.build(userApi);
    }
}
