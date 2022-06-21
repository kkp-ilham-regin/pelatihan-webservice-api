package com.kkp.pelatihanwebservice.internal.services.area;

import com.kkp.pelatihanwebservice.internal.models.Area;

public interface AreaService {
    Area createArea(Area area);

    Area updateArea(Long id, Area area);

    Area deleteArea(Long id);
}
