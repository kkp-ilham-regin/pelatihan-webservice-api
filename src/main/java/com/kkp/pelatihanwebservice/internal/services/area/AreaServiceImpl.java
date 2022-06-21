package com.kkp.pelatihanwebservice.internal.services.area;

import com.kkp.pelatihanwebservice.internal.models.Area;
import com.kkp.pelatihanwebservice.internal.repositories.AreaRepository;
import com.kkp.pelatihanwebservice.internal.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.TransactionScoped;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@TransactionScoped
public class AreaServiceImpl implements AreaService {
    @Autowired
    AreaRepository areaRepository;

    public AreaServiceImpl(AreaRepository areaRepository) {
        super();
        this.areaRepository = areaRepository;
    }

    public Iterable<Area> getAllArea(String name, Pageable pageable) {
        return areaRepository.findByNamaWilayahContains(name, pageable);
    }

    public Area getAreaDetail(Long id) {
        return areaRepository.findAreaById(id);
    }

    @Override
    public Area createArea(Area area) {
        return areaRepository.save(area);
    }

    @Override
    public Area updateArea(Long id, Area area) {
        Area existingArea = areaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Wilayah", "ID", id));

        existingArea.setNamaWilayah(area.getNamaWilayah());
        existingArea.setEmail1(area.getEmail1());
        existingArea.setEmail2(area.getEmail2());
        existingArea.setEmail3(area.getEmail3());
        existingArea.setUpdatedAt(LocalDateTime.now());

        areaRepository.save(existingArea);
        return existingArea;
    }

    @Override
    public Area deleteArea(Long id) {
        Area existingArea = areaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Wilayah", "ID", id));

        areaRepository.delete(existingArea);
        return existingArea;
    }
}
