package com.kkp.pelatihanwebservice.internal.controllers;

import com.kkp.pelatihanwebservice.internal.models.Education;
import com.kkp.pelatihanwebservice.internal.services.education.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600, origins = "*")
@RequestMapping("/pelatihan-webservice/internal-api/v1")
public class EducationController {
    @Autowired
    EducationService educationService;

    @GetMapping("educations")
    public List<Education> getAllEducation() {
        return educationService.getAllEducation();
    }
}
