package com.kkp.pelatihanwebservice.internal.services.offer;

import com.kkp.pelatihanwebservice.internal.models.Offer;

public interface OfferService {
    Offer createOffer(Offer offer);

    Offer approveOrRejectOffer(Long id, Offer offer);
}
