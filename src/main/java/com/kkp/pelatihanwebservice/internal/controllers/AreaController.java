package com.kkp.pelatihanwebservice.internal.controllers;

import com.kkp.pelatihanwebservice.internal.dto.area.request.AreaRequest;
import com.kkp.pelatihanwebservice.internal.dto.area.response.AreaResponse;
import com.kkp.pelatihanwebservice.internal.models.Area;
import com.kkp.pelatihanwebservice.internal.repositories.AreaRepository;
import com.kkp.pelatihanwebservice.internal.services.area.AreaService;
import com.kkp.pelatihanwebservice.internal.services.area.AreaServiceImpl;
import com.kkp.pelatihanwebservice.internal.utils.exceptions.ResourceNotFoundException;
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
@RequestMapping("/pelatihan-webservice/internal-api/v1/areas")
public class AreaController {
    private AreaService areaService;

    @Autowired
    private AreaServiceImpl areaServiceImpl;

    @Autowired
    AreaRepository areaRepository;

    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @GetMapping("")
    public Iterable<Area> areaList(
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
            return areaServiceImpl.getAllArea(searchData, pageable);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @GetMapping("/{id}")
    public Object areaDetail(@PathVariable("id") Long id) {
        if (areaServiceImpl.getAreaDetail(id) == null) {
            return new ResourceNotFoundException("Wilayah", "ID", id);
        }
        return areaServiceImpl.getAreaDetail(id);
    }

    @PostMapping("/")
    public ResponseEntity<AreaResponse<Area>> createArea(
            @Valid @RequestBody AreaRequest areaRequest,
            Errors errors
    ) throws IOException {
        try {
            AreaResponse<Area> responseData = new AreaResponse<>();

            if (errors.hasErrors()) {
                for (ObjectError error : errors.getAllErrors()) {
                    responseData.getMessages().add(error.getDefaultMessage());
                }
                responseData.setStatus(false);
                responseData.setCode(400);
                responseData.setData(null);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }

            Area area = new Area(areaRequest.getNamaWilayah(), areaRequest.getEmail1(), areaRequest.getEmail2(),
                    areaRequest.getEmail3(), areaRequest.getCreatedAt(), areaRequest.getUpdatedAt());

            responseData.setStatus(true);
            responseData.setCode(200);
            responseData.setData(areaServiceImpl.createArea(area));

            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AreaResponse<Area>> updateArea(
            @PathVariable("id") Long id,
            @Valid @RequestBody AreaRequest areaRequest,
            Errors errors
    ) throws IOException {
        try {
            AreaResponse<Area> responseData = new AreaResponse<>();

            if (errors.hasErrors()) {
                for (ObjectError error : errors.getAllErrors()) {
                    responseData.getMessages().add(error.getDefaultMessage());
                }

                responseData.setStatus(false);
                responseData.setCode(400);
                responseData.setData(null);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }

            Area area = new Area(areaRequest.getNamaWilayah(), areaRequest.getEmail1(), areaRequest.getEmail2(),
                    areaRequest.getEmail3(), areaRequest.getCreatedAt(), areaRequest.getUpdatedAt());

            responseData.setStatus(true);
            responseData.setCode(200);
            responseData.setData(areaServiceImpl.updateArea(id, area));

            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @DeleteMapping("/{id}")
    public Area deleteArea(@PathVariable("id") Long id) {
        return areaServiceImpl.deleteArea(id);
    }
}
