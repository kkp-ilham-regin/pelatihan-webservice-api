package com.kkp.pelatihanwebservice.internal.controllers;

import com.kkp.pelatihanwebservice.internal.models.Gender;
import com.kkp.pelatihanwebservice.internal.models.Religion;
import com.kkp.pelatihanwebservice.internal.services.religion.ReligionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600, origins = "*")
@RequestMapping("/pelatihan-webservice/internal-api/v1")
public class ReligionController {
    @Autowired
    ReligionService religionService;

    @GetMapping("/religions")
    public List<Religion> getAllReligion() {
        return religionService.getAllReligion();
    }
}
