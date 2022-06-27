package com.kkp.pelatihanwebservice.internal.controllers;

import com.kkp.pelatihanwebservice.internal.dto.publicUser.request.PublicUserRequest;
import com.kkp.pelatihanwebservice.internal.dto.publicUser.response.PublicUserResponse;
import com.kkp.pelatihanwebservice.internal.repositories.UserApiRepository;
import com.kkp.pelatihanwebservice.internal.security.jwt.JwtUtils;
import com.kkp.pelatihanwebservice.internal.security.services.UserApiDetailsImpl;
import com.kkp.pelatihanwebservice.internal.dto.requests.LoginRequest;
import com.kkp.pelatihanwebservice.internal.dto.response.JwtResponse;
import com.kkp.pelatihanwebservice.internal.models.UserApi;
import com.kkp.pelatihanwebservice.internal.repositories.RoleRepository;
import com.kkp.pelatihanwebservice.internal.services.userApi.UserApiService;
import com.kkp.pelatihanwebservice.internal.services.userApi.UserApiServiceImpl;
import com.kkp.pelatihanwebservice.internal.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/pelatihan-webservice/internal-api/v1/auth")
public class AuthController {
    private UserApiService userApiService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserApiRepository userApiRepository;

    @Autowired
    private UserApiServiceImpl userApiServiceImpl;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    public AuthController(UserApiService userApiService) {
        this.userApiService = userApiService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserApiDetailsImpl userApiDetails = (UserApiDetailsImpl) authentication.getPrincipal();

        if (!userApiDetails.isAdmin()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResourceNotFoundException("Employee", "Email", userApiDetails.getEmail()));
        }

        return ResponseEntity.ok(
                new JwtResponse(userApiDetails.getId(), userApiDetails.getFullname(),
                        userApiDetails.getEmail(), userApiDetails.getEmailVerifiedAt(), userApiDetails.isAdmin(), jwt)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserApiDetailsImpl userApiDetails = (UserApiDetailsImpl) authentication.getPrincipal();

        if (userApiDetails.isAdmin()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResourceNotFoundException("User", "Email", userApiDetails.getEmail()));
        }

        return ResponseEntity.ok(
                new JwtResponse(userApiDetails.getId(), userApiDetails.getFullname(),
                        userApiDetails.getEmail(), userApiDetails.getEmailVerifiedAt(), userApiDetails.isAdmin(), jwt)
        );
    }

    @PostMapping("/register")
    public ResponseEntity<PublicUserResponse<UserApi>> publicUserRegister(@Valid @RequestBody PublicUserRequest publicUserRequest,
                                                                          Errors errors) {
        PublicUserResponse<UserApi> responseData = new PublicUserResponse<>();

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
                publicUserRequest.getEmailVerifiedAt(), publicUserRequest.isAdmin(), publicUserRequest.getCreatedAt(),
                publicUserRequest.getUpdatedAt());
        responseData.setStatus(true);
        responseData.setData(userApiServiceImpl.createUserApi(userApi));
        responseData.setCode(200);

        return ResponseEntity.ok(responseData);
    }

}
