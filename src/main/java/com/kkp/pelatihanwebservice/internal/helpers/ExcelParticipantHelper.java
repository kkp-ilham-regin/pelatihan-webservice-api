package com.kkp.pelatihanwebservice.internal.helpers;

import com.kkp.pelatihanwebservice.internal.models.ParticipantInternal;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelParticipantHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = {"Nama Peserta", "NIP", "Nomor KTA", "Expired KTA", "Jabatan", "Lokasi Kerja", "NIK", "Tanggal TMT Masuk",
            "Alamat", "Tempat Lahir", "Tanggal Lahir", "Golongan Darah", "Nomor Telepon", "Nama Sekolah", "Email", "NPWP", "Url Image",
            "ID Wilayah", "ID JenisKelamin", "ID Agama", "ID Status Pernikahan", "ID Pendidikan", "ID Status Pegawai", "ID Penawaran"};

    static String SHEET = "Participants";

    public static boolean hasExcelFormat(MultipartFile multipartFile) {
        if (!TYPE.equals(multipartFile.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<ParticipantInternal> excelToParticipants(InputStream inputStream) {
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            List<ParticipantInternal> participantInternals = new ArrayList<ParticipantInternal>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                //skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                ParticipantInternal participant = new ParticipantInternal();
                int cellIndex = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIndex) {
                        case 0:
                            participant.setNamaPeserta(currentCell.getStringCellValue());
                            break;
                        case 1:
                            participant.setNip(currentCell.getStringCellValue());
                            break;
                        case 2:
                            participant.setNomorKta(currentCell.getStringCellValue());
                            break;
                        case 3:
                            participant.setExpiredKta(currentCell.getDateCellValue());
                            break;
                        case 4:
                            participant.setJabatan(currentCell.getStringCellValue());
                            break;
                        case 5:
                            participant.setLokasiKerja(currentCell.getStringCellValue());
                            break;
                        case 6:
                            participant.setNik(currentCell.getStringCellValue());
                            break;
                        case 7:
                            participant.setTanggalTmtMasuk(currentCell.getDateCellValue());
                            break;
                        case 8:
                            participant.setAlamat(currentCell.getStringCellValue());
                            break;
                        case 9:
                            participant.setTempatLahir(currentCell.getStringCellValue());
                            break;
                        case 10:
                            participant.setTanggalLahir(currentCell.getDateCellValue());
                            break;
                        case 11:
                            participant.setGolonganDarah(currentCell.getStringCellValue());
                            break;
                        case 12:
                            participant.setNomorTelepon(currentCell.getStringCellValue());
                            break;
                        case 13:
                            participant.setNamaSekolah(currentCell.getStringCellValue());
                            break;
                        case 14:
                            participant.setEmail(currentCell.getStringCellValue());
                            break;
                        case 15:
                            participant.setNpwp(currentCell.getStringCellValue());
                            break;
                        case 16:
                            participant.setUrlImage(currentCell.getStringCellValue());
                            break;
                        case 17:
                            participant.setIdWilayah((long) currentCell.getNumericCellValue());
                            break;
                        case 18:
                            participant.setIdJenisKelamin((long) currentCell.getNumericCellValue());
                            break;
                        case 19:
                            participant.setIdAgama((long) currentCell.getNumericCellValue());
                            break;
                        case 20:
                            participant.setIdStatusPernikahan((long) currentCell.getNumericCellValue());
                            break;
                        case 21:
                            participant.setIdPendidikan((long) currentCell.getNumericCellValue());
                            break;
                        case 22:
                            participant.setIdStatusPegawai((long) currentCell.getNumericCellValue());
                            break;
                        case 23:
                            participant.setIdPenawaran((long) currentCell.getNumericCellValue());
                            break;
                    }
                    participant.setCreatedAt(LocalDateTime.now());
                    cellIndex++;
                }
                participantInternals.add(participant);
            }
            workbook.close();
            return participantInternals;
        } catch (IOException e) {
            throw new RuntimeException("Fail to parse Excel file: " + e);
        }
    }
}
