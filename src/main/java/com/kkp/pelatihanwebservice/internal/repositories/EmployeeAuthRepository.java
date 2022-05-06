package com.kkp.pelatihanwebservice.internal.repositories;

import com.kkp.pelatihanwebservice.internal.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

//TODO: deprecated interface
public interface EmployeeAuthRepository extends JpaRepository<Employee, Long> {
    Employee findEmployeeByEmail(String email);
}
