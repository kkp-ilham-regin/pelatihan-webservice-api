package com.kkp.pelatihanwebservice.internal.repositories;

import com.kkp.pelatihanwebservice.internal.models.UserApi;
import org.springframework.data.jpa.repository.JpaRepository;

//TODO: deprecated interface
public interface EmployeeAuthRepository extends JpaRepository<UserApi, Long> {
    UserApi findEmployeeByEmail(String email);
}
