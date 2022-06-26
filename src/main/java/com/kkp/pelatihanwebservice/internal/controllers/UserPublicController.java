package com.kkp.pelatihanwebservice.internal.controllers;

import com.kkp.pelatihanwebservice.internal.dto.employee.request.EmployeeRequest;
import com.kkp.pelatihanwebservice.internal.dto.employee.response.EmployeeResponse;
import com.kkp.pelatihanwebservice.internal.dto.publicUser.request.PublicUserRequest;
import com.kkp.pelatihanwebservice.internal.dto.publicUser.response.PublicUserResponse;
import com.kkp.pelatihanwebservice.internal.models.UserApi;
import com.kkp.pelatihanwebservice.internal.repositories.UserApiRepository;
import com.kkp.pelatihanwebservice.internal.services.userApi.UserApiService;
import com.kkp.pelatihanwebservice.internal.services.userApi.UserApiServiceImpl;
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
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("pelatihan-webservice/internal-api/v1/users")
public class UserPublicController {
    private UserApiService userApiService;

    @Autowired
    private UserApiServiceImpl userApiServiceImpl;

    @Autowired
    UserApiRepository userApiRepository;

    @Autowired
    PasswordEncoder encoder;

    public UserPublicController(UserApiService userApiService) {
        this.userApiService = userApiService;
    }

    @GetMapping("/{id}")
    public Object userDetail(@PathVariable("id") Long id) {
        if (userApiServiceImpl.findUserApiById(id) == null) {
            return new ResourceNotFoundException("User", "ID", id);
        }
        return userApiServiceImpl.findUserApiById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicUserResponse<UserApi>> userUpdate(@Valid @RequestBody PublicUserRequest publicUserRequest,
                                                                  Errors errors, @PathVariable("id") Long id) {
        PublicUserResponse<UserApi> responseData = new PublicUserResponse<>();

        if (userApiRepository.findUserApiById(id) == null) {
            String[] errorMessages = {
                    "User not found"
            };
            responseData.setData(null);
            responseData.setStatus(false);
            responseData.setCode(404);
            for (String message : errorMessages) {
                responseData.getMessages().add(message);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        if (publicUserRequest.getEmail().equals(userApiServiceImpl.findUserApiById(id).getEmail())) {
            if (errors.hasErrors()) {
                for (ObjectError error : errors.getAllErrors()) {
                    responseData.getMessages().add(error.getDefaultMessage());
                }
                responseData.setStatus(false);
                responseData.setCode(400);
                responseData.setData(null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }

            UserApi userApi = new UserApi(publicUserRequest.getName(), publicUserRequest.getEmail(), encoder.encode(publicUserRequest.getPassword()),
                    publicUserRequest.getEmailVerifiedAt(), publicUserRequest.isAdmin(), publicUserRequest.getCreatedAt(),
                    publicUserRequest.getUpdatedAt());
            responseData.setStatus(true);
            responseData.setCode(200);
            responseData.setData(userApiServiceImpl.updateUserApi(id, userApi));
            return ResponseEntity.ok(responseData);
        }


        if (userApiRepository.existsUserApiByEmail(publicUserRequest.getEmail())) {
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

        UserApi userApi = new UserApi(publicUserRequest.getName(), publicUserRequest.getEmail(), encoder.encode(publicUserRequest.getPassword()),
                publicUserRequest.getEmailVerifiedAt(), publicUserRequest.isAdmin(), publicUserRequest.getCreatedAt(), publicUserRequest.getUpdatedAt());
        responseData.setStatus(true);
        responseData.setCode(200);
        responseData.setData(userApiServiceImpl.updateUserApi(id, userApi));
        return ResponseEntity.ok(responseData);
    }

}
