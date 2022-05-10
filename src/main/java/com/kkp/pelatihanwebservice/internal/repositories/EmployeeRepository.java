package com.kkp.pelatihanwebservice.internal.repositories;

import com.kkp.pelatihanwebservice.internal.models.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findEmployeeByEmail(String email);

    Boolean existsEmployeeByEmail(String email);

    Page<Employee> findByFullnameContains(String name, Pageable pageable);

    Employee findEmployeeById(Long id);

    Optional<Employee> findById(Long id);
}
