package com.kkp.pelatihanwebservice.internal.services.employeeStatus;

import com.kkp.pelatihanwebservice.internal.models.EmployeeStatus;

public interface EmployeeStatusService {
    EmployeeStatus createEmployeeStatus(EmployeeStatus employeeStatus);

    EmployeeStatus updateEmployeeStatus(Long id, EmployeeStatus employeeStatus);

    EmployeeStatus deleteEmployeeStatus(Long id);
}
