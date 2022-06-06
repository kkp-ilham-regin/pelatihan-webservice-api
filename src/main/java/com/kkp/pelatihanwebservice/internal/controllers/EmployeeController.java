package com.kkp.pelatihanwebservice.internal.controllers;

import com.kkp.pelatihanwebservice.internal.dto.employee.request.EmployeeRequest;
import com.kkp.pelatihanwebservice.internal.dto.employee.response.EmployeeResponse;
import com.kkp.pelatihanwebservice.internal.models.Employee;
import com.kkp.pelatihanwebservice.internal.repositories.EmployeeRepository;
import com.kkp.pelatihanwebservice.internal.services.employee.EmployeeService;
import com.kkp.pelatihanwebservice.internal.services.employee.EmployeeServiceImpl;
import com.kkp.pelatihanwebservice.internal.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@CrossOrigin(maxAge = 3600, origins = "*")
@RequestMapping("/test/employees") //TODO: will be rolledback
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PasswordEncoder encoder;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("")
    public Iterable<Employee> employeeList(@RequestParam(required = false, value = "search", defaultValue = "") String searhData,
                                           @RequestParam(required = false, value = "size", defaultValue = "5") int size,
                                           @RequestParam(required = false, value = "page", defaultValue = "0") int page,
                                           @RequestParam(required = false, value = "sort", defaultValue = "desc") String sort)
            throws IOException {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
            if (sort.equalsIgnoreCase("asc")) {
                pageable = PageRequest.of(page, size, Sort.by("id").ascending());
            }
            return employeeServiceImpl.findAllEmployee(searhData, pageable);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @GetMapping("/{id}")
    public Object employeeDetail(@PathVariable("id") Long id) {
        if (employeeServiceImpl.findEmployeeById(id) == null) {
            return new ResourceNotFoundException("Employee", "ID", id);
        }
        return employeeServiceImpl.findEmployeeById(id);
    }

    @PostMapping("/")
    public ResponseEntity<EmployeeResponse<Employee>> employeeCreate(@Valid @RequestBody EmployeeRequest employeeRequest,
                                                                     Errors errors) {
        EmployeeResponse<Employee> responseData = new EmployeeResponse<>();

        if (employeeRepository.existsEmployeeByEmail(employeeRequest.getEmail())) {
            String[] errorMessages = {
                    "Email sudah digunakan"
            };
            responseData.setData(null);
            responseData.setStatus(false);
            responseData.setCode(401);
            for (String message : errorMessages) {
                responseData.getMessages().add(message);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setCode(400);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Employee employee = new Employee(employeeRequest.getName(), employeeRequest.getEmail(), encoder.encode(employeeRequest.getPassword()),
                employeeRequest.getEmailVerifiedAt(), employeeRequest.getCreatedAt(), employeeRequest.getUpdatedAt());
        responseData.setStatus(true);
        responseData.setData(employeeServiceImpl.createEmployee(employee));
        responseData.setCode(200);

        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse<Employee>> employeeUpdate(@Valid @RequestBody EmployeeRequest employeeRequest,
                                                                     Errors errors, @PathVariable("id") Long id) {
        EmployeeResponse<Employee> responseData = new EmployeeResponse<>();

        if (employeeRequest.getEmail().equals(employeeServiceImpl.findEmployeeById(id).getEmail())) {
            if (errors.hasErrors()) {
                for (ObjectError error : errors.getAllErrors()) {
                    responseData.getMessages().add(error.getDefaultMessage());
                }
                responseData.setStatus(false);
                responseData.setCode(400);
                responseData.setData(null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }

            Employee employee = new Employee(employeeRequest.getName(), employeeRequest.getEmail(), encoder.encode(employeeRequest.getPassword()),
                    employeeRequest.getEmailVerifiedAt(), employeeRequest.getCreatedAt(), employeeRequest.getUpdatedAt());
            responseData.setStatus(true);
            responseData.setCode(200);
            responseData.setData(employeeServiceImpl.updateEmployee(id, employee));
            return ResponseEntity.ok(responseData);
        }


        if (employeeRepository.existsEmployeeByEmail(employeeRequest.getEmail())) {
            String[] errorMessages = {
                    "Email sudah digunakan"
            };
            responseData.setData(null);
            responseData.setStatus(false);
            responseData.setCode(401);
            for (String message : errorMessages) {
                responseData.getMessages().add(message);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setCode(400);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Employee employee = new Employee(employeeRequest.getName(), employeeRequest.getEmail(), encoder.encode(employeeRequest.getPassword()),
                employeeRequest.getEmailVerifiedAt(), employeeRequest.getCreatedAt(), employeeRequest.getUpdatedAt());
        responseData.setStatus(true);
        responseData.setCode(200);
        responseData.setData(employeeServiceImpl.updateEmployee(id, employee));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public Employee employeeDelete(@PathVariable("id") Long id) {
        return employeeServiceImpl.deleteEmployee(id);
    }
}
