package com.kkp.pelatihanwebservice.internal.services.offer;

import com.kkp.pelatihanwebservice.internal.models.Offer;
import com.kkp.pelatihanwebservice.internal.repositories.OfferRepository;
import com.kkp.pelatihanwebservice.internal.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.TransactionScoped;
import java.time.LocalDateTime;

@Service
@TransactionScoped
public class OfferServiceImpl implements OfferService {

    @Autowired
    OfferRepository offerRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
        super();
        this.offerRepository = offerRepository;
    }

    public Iterable<Offer> findAllOfferByUserId(Long userId, Pageable pageable) {
        return offerRepository.findAllByUserId(userId, pageable);
    }

    public Iterable<Offer> findAllOffer(String companyName, Pageable pageable) {
        return offerRepository.findByNamaPerusahaanContainingIgnoreCase(companyName, pageable);
    }

    public Offer getOfferDetail(Long id) {
        return offerRepository.findOfferById(id);
    }


    @Override
    public Offer createOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    @Override
    public Offer approveOrRejectOffer(Long id, Offer offer) {
        Offer existingOffer = offerRepository.findOfferById(id);

        if (existingOffer == null) {
            offerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Penawaran", "ID", id));
        }

        existingOffer.setStatusId(offer.getStatusId());
        existingOffer.setTrainerId(offer.getTrainerId());
        existingOffer.setUpdatedAt(LocalDateTime.now());

        existingOffer.setStatus(offer.getStatus());
        existingOffer.setTrainer(offer.getTrainer());

        offerRepository.save(existingOffer);
        return existingOffer;
    }

}
