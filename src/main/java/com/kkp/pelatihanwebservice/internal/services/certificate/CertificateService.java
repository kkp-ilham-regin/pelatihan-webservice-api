package com.kkp.pelatihanwebservice.internal.services.certificate;

import com.kkp.pelatihanwebservice.internal.models.Certificate;

public interface CertificateService {
    Certificate createCertificate(Certificate certificate);

    Certificate updateCertificate(Long id, Certificate certificate);

    Certificate deleteCertificate(Long id);
}
