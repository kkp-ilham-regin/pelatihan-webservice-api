package com.kkp.pelatihanwebservice.internal.services.employee;

import com.kkp.pelatihanwebservice.internal.models.Employee;

public interface EmployeeService {
    Employee createEmployee(Employee employee);

    Employee updateEmployee(Long id, Employee employee);

    Employee deleteEmployee(Long id);
}
