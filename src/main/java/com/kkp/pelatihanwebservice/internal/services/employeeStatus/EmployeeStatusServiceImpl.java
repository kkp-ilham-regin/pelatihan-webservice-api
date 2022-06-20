package com.kkp.pelatihanwebservice.internal.services.employeeStatus;

import com.kkp.pelatihanwebservice.internal.models.EmployeeStatus;
import com.kkp.pelatihanwebservice.internal.repositories.EmployeeStatusRepository;
import com.kkp.pelatihanwebservice.internal.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.TransactionScoped;
import java.time.LocalDateTime;

@Service
@TransactionScoped
public class EmployeeStatusServiceImpl implements EmployeeStatusService {

    @Autowired
    EmployeeStatusRepository employeeStatusRepository;

    public EmployeeStatusServiceImpl(EmployeeStatusRepository employeeStatusRepository) {
        super();
        this.employeeStatusRepository = employeeStatusRepository;
    }

    public Iterable<EmployeeStatus> getAllEmployeeStatus(String name, Pageable pageable) {
        return employeeStatusRepository.findByNamaStatusContains(name, pageable);
    }

    public EmployeeStatus getEmployeeStatusDetail(Long id) {
        return employeeStatusRepository.findEmployeeStatusById(id);
    }

    @Override
    public EmployeeStatus createEmployeeStatus(EmployeeStatus employeeStatus) {
        return employeeStatusRepository.save(employeeStatus);
    }

    @Override
    public EmployeeStatus updateEmployeeStatus(Long id, EmployeeStatus employeeStatus) {
        EmployeeStatus existingEmployeeStatus = employeeStatusRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Status Karyawan", "ID", id));

        existingEmployeeStatus.setNamaStatus(employeeStatus.getNamaStatus());
        existingEmployeeStatus.setUpdatedAt(LocalDateTime.now());

        employeeStatusRepository.save(existingEmployeeStatus);
        return existingEmployeeStatus;
    }

    @Override
    public EmployeeStatus deleteEmployeeStatus(Long id) {
        EmployeeStatus existingEmployeeStatus = employeeStatusRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Status Karyawan", "ID", id));

        employeeStatusRepository.delete(existingEmployeeStatus);
        return existingEmployeeStatus;
    }
}
