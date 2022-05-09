package com.kkp.pelatihanwebservice.internal.services.employee;

import com.kkp.pelatihanwebservice.internal.models.Employee;
import com.kkp.pelatihanwebservice.internal.repositories.EmployeeRepository;
import com.kkp.pelatihanwebservice.internal.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.TransactionScoped;
import java.time.LocalDateTime;

@Service
@TransactionScoped
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    public Iterable<Employee> findAllEmployee(String name, Pageable pageable) {
        return employeeRepository.findByFullnameContainsAndDeletedAtIsNull(name, pageable);
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepository.findEmployeeByIdAndDeletedAtIsNull(id);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existingEmployee = employeeRepository.findByIdAndDeletedAtIsNull(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "ID", id));

        existingEmployee.setFullname(employee.getFullname());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPassword(employee.getPassword());

        employeeRepository.save(existingEmployee);
        return existingEmployee;
    }

    @Override
    public Employee deleteEmployee(Long id) {
        Employee existingEmployee = employeeRepository.findByIdAndDeletedAtIsNull(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "ID", id));
        existingEmployee.setDeletedAt(LocalDateTime.now());
        employeeRepository.save(existingEmployee);
        return existingEmployee;
    }
}
