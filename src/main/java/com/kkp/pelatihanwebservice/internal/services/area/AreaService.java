package com.kkp.pelatihanwebservice.internal.services.area;

import com.kkp.pelatihanwebservice.internal.models.Area;
import com.kkp.pelatihanwebservice.internal.repositories.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AreaService {
    @Autowired
    AreaRepository areaRepository;

    public List<Area> getAllArea() {
        List<Area> areas = new ArrayList<Area>();
        areaRepository.findAll().forEach(area -> areas.add(area));
        return areas;
    }
}
