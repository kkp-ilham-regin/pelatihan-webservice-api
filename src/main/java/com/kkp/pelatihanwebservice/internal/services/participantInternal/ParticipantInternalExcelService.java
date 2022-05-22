package com.kkp.pelatihanwebservice.internal.services.participantInternal;

import com.kkp.pelatihanwebservice.internal.helpers.ExcelParticipantHelper;
import com.kkp.pelatihanwebservice.internal.models.ParticipantInternal;
import com.kkp.pelatihanwebservice.internal.repositories.ParticipantInternalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ParticipantInternalExcelService {
    @Autowired
    ParticipantInternalRepository participantInternalRepository;

    public void save(MultipartFile file) {
        try {
            List<ParticipantInternal> participants = ExcelParticipantHelper.excelToParticipants(file.getInputStream());
            participantInternalRepository.saveAll(participants);
        } catch (IOException e) {
            throw new RuntimeException("Fail to store excel data: " + e.getMessage());
        }
    }
}
