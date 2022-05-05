package com.kkp.pelatihanwebservice.internal.services.banner;

import com.kkp.pelatihanwebservice.internal.models.Banner;

public interface BannerService {
    Banner createBanner(Banner banner);

    Banner updateBanner(Long id, Banner banner);

    Banner deletedBanner(Long id);
}
