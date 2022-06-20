package com.kkp.pelatihanwebservice.internal.repositories;

import com.kkp.pelatihanwebservice.internal.models.EmployeeStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeStatusRepository extends JpaRepository<EmployeeStatus, Long> {
    Page<EmployeeStatus> findByNamaStatusContains(String name, Pageable pageable);

    EmployeeStatus findEmployeeStatusById(Long id);

    Optional<EmployeeStatus> findById(Long id);
}
