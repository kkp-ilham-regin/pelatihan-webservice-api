package com.kkp.pelatihanwebservice.internal.controllers;

import com.kkp.pelatihanwebservice.external.utils.exceptions.ResourceNotFoundException;
import com.kkp.pelatihanwebservice.internal.dto.employeeStatus.request.EmployeeStatusRequest;
import com.kkp.pelatihanwebservice.internal.dto.employeeStatus.response.EmployeeStatusResponse;
import com.kkp.pelatihanwebservice.internal.models.EmployeeStatus;
import com.kkp.pelatihanwebservice.internal.repositories.EmployeeStatusRepository;
import com.kkp.pelatihanwebservice.internal.services.employeeStatus.EmployeeStatusService;
import com.kkp.pelatihanwebservice.internal.services.employeeStatus.EmployeeStatusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@CrossOrigin(maxAge = 3600, origins = "*")
@RequestMapping("/pelatihan-webservice/internal-api/v1/employee-statuses")
public class EmployeeStatusController {

    private EmployeeStatusService employeeStatusService;

    @Autowired
    private EmployeeStatusServiceImpl employeeStatusServiceImpl;

    @Autowired
    EmployeeStatusRepository employeeStatusRepository;

    public EmployeeStatusController(EmployeeStatusService employeeStatusService) {
        this.employeeStatusService = employeeStatusService;
    }

    @GetMapping("")
    public Iterable<EmployeeStatus> employeeStatusList(
            @RequestParam(required = false, name = "search", defaultValue = "") String searchData,
            @RequestParam(required = false, name = "size", defaultValue = "5") int size,
            @RequestParam(required = false, name = "page", defaultValue = "0") int page,
            @RequestParam(required = false, name = "sort", defaultValue = "desc") String sort
    ) throws IOException {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

            if (sort.equalsIgnoreCase("asc")) {
                pageable = PageRequest.of(page, size, Sort.by("id").ascending());
            }

            return employeeStatusServiceImpl.getAllEmployeeStatus(searchData, pageable);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @GetMapping("/{id}")
    public Object employeeStatusDetail(@PathVariable("id") Long id) {
        if (employeeStatusServiceImpl.getEmployeeStatusDetail(id) == null) {
            throw new ResourceNotFoundException("Status Karyawan", "ID", id);
        }
        return employeeStatusServiceImpl.getEmployeeStatusDetail(id);
    }

    @PostMapping("/")
    public ResponseEntity<EmployeeStatusResponse<EmployeeStatus>> createEmployeeStatus(
            @Valid @RequestBody EmployeeStatusRequest employeeStatusRequest,
            Errors errors
    ) throws IOException {
        try {
            EmployeeStatusResponse<EmployeeStatus> responseData = new EmployeeStatusResponse<>();

            if (errors.hasErrors()) {
                for (ObjectError error : errors.getAllErrors()) {
                    responseData.getMessages().add(error.getDefaultMessage());
                }
                responseData.setCode(400);
                responseData.setStatus(false);
                responseData.setData(null);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }

            EmployeeStatus employeeStatus = new EmployeeStatus(employeeStatusRequest.getNamaStatus(),
                    employeeStatusRequest.getCreatedAt(), employeeStatusRequest.getUpdatedAt());

            responseData.setCode(200);
            responseData.setStatus(true);
            responseData.setData(employeeStatusServiceImpl.createEmployeeStatus(employeeStatus));
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeStatusResponse<EmployeeStatus>> updateEmployeeStatus(
            @Valid @RequestBody EmployeeStatusRequest employeeStatusRequest,
            @PathVariable("id") Long id,
            Errors errors
    ) throws IOException {
        try {
            EmployeeStatusResponse<EmployeeStatus> responseData = new EmployeeStatusResponse<>();

            if (errors.hasErrors()) {
                for (ObjectError error : errors.getAllErrors()) {
                    responseData.getMessages().add(error.getDefaultMessage());
                }
                responseData.setStatus(false);
                responseData.setCode(400);
                responseData.setData(null);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }

            EmployeeStatus employeeStatus = new EmployeeStatus(employeeStatusRequest.getNamaStatus(),
                    employeeStatusRequest.getCreatedAt(), employeeStatusRequest.getUpdatedAt());

            responseData.setStatus(true);
            responseData.setCode(200);
            responseData.setData(employeeStatusServiceImpl.updateEmployeeStatus(id, employeeStatus));

            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @DeleteMapping("/{id}")
    public EmployeeStatus employeeStatusDelete(@PathVariable("id") Long id) throws IOException {
        try {
            return employeeStatusServiceImpl.deleteEmployeeStatus(id);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}
