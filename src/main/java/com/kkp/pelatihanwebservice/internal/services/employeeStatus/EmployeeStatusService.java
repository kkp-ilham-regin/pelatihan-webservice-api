package com.kkp.pelatihanwebservice.internal.services.employeeStatus;

import com.kkp.pelatihanwebservice.internal.models.EmployeeStatus;
import com.kkp.pelatihanwebservice.internal.repositories.EmployeeStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeStatusService {
    @Autowired
    EmployeeStatusRepository employeeStatusRepository;

    public List<EmployeeStatus> getAllEmployeeStatus() {
        List<EmployeeStatus> employeeStatuses = new ArrayList<EmployeeStatus>();
        employeeStatusRepository.findAll().forEach(employeeStatus -> employeeStatuses.add(employeeStatus));
        return employeeStatuses;
    }
}
