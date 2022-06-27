package com.kkp.pelatihanwebservice.internal.repositories;

import com.kkp.pelatihanwebservice.internal.models.UserApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserApiRepository extends JpaRepository<UserApi, Long> {
    Optional<UserApi> findEmployeeByEmail(String email);

    Boolean existsEmployeeByEmail(String email);


    UserApi findEmployeeById(Long id);

    Optional<UserApi> findById(Long id);

    // ---------------------------------------------

    Optional<UserApi> findUserApiByEmail(String email);

    Boolean existsUserApiByEmail(String email);

    Page<UserApi> findByFullnameContainsAndAdminStatusIsTrue(String name, Pageable pageable);

    UserApi findUserApiById(Long id);
}
