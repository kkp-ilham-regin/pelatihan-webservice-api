package com.kkp.pelatihanwebservice.internal.controllers;

import com.kkp.pelatihanwebservice.internal.repositories.EmployeeRepository;
import com.kkp.pelatihanwebservice.internal.security.jwt.JwtUtils;
import com.kkp.pelatihanwebservice.internal.security.services.EmployeeDetailsImpl;
import com.kkp.pelatihanwebservice.internal.dto.requests.LoginRequest;
import com.kkp.pelatihanwebservice.internal.dto.requests.SignupRequest;
import com.kkp.pelatihanwebservice.internal.dto.response.JwtResponse;
import com.kkp.pelatihanwebservice.internal.dto.response.MessageResponse;
import com.kkp.pelatihanwebservice.internal.models.Employee;
import com.kkp.pelatihanwebservice.internal.models.Role;
import com.kkp.pelatihanwebservice.internal.repositories.RoleRepository;
import com.kkp.pelatihanwebservice.internal.utils.enums.ERole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/pelatihan-webservice/internal-api/v1/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        EmployeeDetailsImpl employeeDetails = (EmployeeDetailsImpl) authentication.getPrincipal();
        List<String> roles = employeeDetails.getAuthorities().stream()
                .map(item -> item.getAuthority()).collect(Collectors.toList());

        return ResponseEntity.ok(
                new JwtResponse(employeeDetails.getId(), employeeDetails.getFullname(),
                        employeeDetails.getEmail(), roles, jwt)
        );
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerEmployee(@Valid @RequestBody SignupRequest signupRequest) {
        if (employeeRepository.existsEmployeeByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(401, false, "Email sudah digunakan"));
        }

        // Create new Employee
        Employee employee = new Employee(signupRequest.getFullname(),
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()),
                signupRequest.getCreatedAt(),
                signupRequest.getUpdatedAt());

        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role superAdminRole = roleRepository.findByName(ERole.SUPER_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(superAdminRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role csRole = roleRepository.findByName(ERole.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(csRole);
                        break;
                    case "content writer":
                        Role maintainerRole = roleRepository.findByName(ERole.CONTENT_WRITER)
                                .orElseThrow(() -> new RuntimeException("Error: Role maintainer is not found."));
                        roles.add(maintainerRole);
                        break;
                    default:
                        Role superAdminRole = roleRepository.findByName(ERole.SUPER_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(superAdminRole);
                }
            });
        }
        employee.setRoles(roles);
        employeeRepository.save(employee);

        return ResponseEntity.ok(new MessageResponse(201, true, "Registrasi pegawai berhasil"));
    }

}
