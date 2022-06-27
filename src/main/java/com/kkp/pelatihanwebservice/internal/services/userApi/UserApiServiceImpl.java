package com.kkp.pelatihanwebservice.internal.services.userApi;

import com.kkp.pelatihanwebservice.internal.models.UserApi;
import com.kkp.pelatihanwebservice.internal.repositories.UserApiRepository;
import com.kkp.pelatihanwebservice.internal.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.TransactionScoped;
import java.time.LocalDateTime;

@Service
@TransactionScoped
public class UserApiServiceImpl implements UserApiService {

    @Autowired
    private UserApiRepository userApiRepository;

    public UserApiServiceImpl(UserApiRepository userApiRepository) {
        super();
        this.userApiRepository = userApiRepository;
    }

    // service of user admin
    public Iterable<UserApi> findAllEmployee(String name, Pageable pageable) {
        return userApiRepository.findByFullnameContainsAndAdminStatusIsTrue(name, pageable);
    }

    public UserApi findEmployeeById(Long id) {
        return userApiRepository.findEmployeeById(id);
    }

    @Override
    public UserApi createEmployee(UserApi userApi) {
        return userApiRepository.save(userApi);
    }

    @Override
    public UserApi updateEmployee(Long id, UserApi userApi) {
        UserApi existingUserApi = userApiRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "ID", id));

        existingUserApi.setFullname(userApi.getFullname());
        existingUserApi.setEmail(userApi.getEmail());
        existingUserApi.setPassword(userApi.getPassword());
        existingUserApi.setAdminStatus(true);
        existingUserApi.setUpdatedAt(LocalDateTime.now());

        userApiRepository.save(existingUserApi);
        return existingUserApi;
    }

    @Override
    public UserApi deleteEmployee(Long id) {
        UserApi existingUserApi = userApiRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "ID", id));
        userApiRepository.delete(existingUserApi);
        return existingUserApi;
    }

    // services of Public User
    public UserApi findUserApiById(Long id) {
        return userApiRepository.findUserApiById(id);
    }

    @Override
    public UserApi createUserApi(UserApi userApi) {
        return userApiRepository.save(userApi);
    }

    @Override
    public UserApi updateUserApi(Long id, UserApi userApi) {
        UserApi existingUserApi = userApiRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "ID", id));

        existingUserApi.setFullname(userApi.getFullname());
        existingUserApi.setEmail(userApi.getEmail());
        existingUserApi.setPassword(userApi.getPassword());
        existingUserApi.setAdminStatus(false);
        existingUserApi.setUpdatedAt(LocalDateTime.now());

        userApiRepository.save(existingUserApi);
        return existingUserApi;
    }

    @Override
    public UserApi deleteUserApi(Long id) {
        UserApi existingUserApi = userApiRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "ID", id));
        userApiRepository.delete(existingUserApi);
        return existingUserApi;
    }
}
