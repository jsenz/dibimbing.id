package com.dibimbing.assignment3.service.impl;

import com.dibimbing.assignment3.model.Karyawan;
import com.dibimbing.assignment3.model.KaryawanTraining;
import com.dibimbing.assignment3.model.Rekening;
import com.dibimbing.assignment3.model.Training;
import com.dibimbing.assignment3.repository.KaryawanRepository;
import com.dibimbing.assignment3.repository.RekeningRepository;
import com.dibimbing.assignment3.service.RekeningService;
import com.dibimbing.assignment3.utils.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class RekeningServiceImpl implements RekeningService {

    @Autowired
    public KaryawanRepository karyawanRepository;

    @Autowired
    public TemplateResponse templateResponse;

    @Autowired
    public RekeningRepository rekeningRepository;

    @Override
    public Map insert(Rekening rekening, Long karyawan_id) {

        try {
            if (templateResponse.chekNull(rekening.getNama())) {
                return templateResponse.templateEror("Nama is Requiered");
            }
            if(templateResponse.chekNull(karyawan_id)){
                return   templateResponse.templateEror("Id Karyawan Tidak boleh null");
            }
            Karyawan checkIdKaryawan = karyawanRepository.getbyIDKaryawan(karyawan_id);
            if (templateResponse.chekNull(checkIdKaryawan)) {
                return templateResponse.templateEror("Id Karyawan Not found");
            }

            rekening.setKaryawan(checkIdKaryawan);
            Rekening rekeningSave = rekeningRepository.save(rekening);
            return templateResponse.templateSukses(rekeningSave);
        } catch (Exception e) {
            return templateResponse.templateEror(e);
        }
    }

    @Override
    public Map update(Rekening rekening, Long karyawan_id) {
        try {
            if(templateResponse.chekNull(karyawan_id)){
                return   templateResponse.templateEror("Id Karyawan is required");
            }
            Karyawan checkIdKaryawan = karyawanRepository.getbyIDKaryawan(karyawan_id);
            if (templateResponse.chekNull(checkIdKaryawan)) {
                return templateResponse.templateEror("Id Karyawan Not found");
            }
            if (templateResponse.chekNull(rekening.getId())) {
                return templateResponse.templateEror("Id Rekening is required");
            }
            Rekening chekIdRekening = rekeningRepository.getbyID(rekening.getId());
            if (templateResponse.chekNull(chekIdRekening)) {
                return templateResponse.templateEror("Id Rekening Not found");
            }

            rekening.setKaryawan(checkIdKaryawan);
            chekIdRekening.setKaryawan(rekening.getKaryawan());
            chekIdRekening.setNama(rekening.getNama());
            chekIdRekening.setJenis(rekening.getJenis());
            chekIdRekening.setNomor(rekening.getNomor());

            Rekening doSave = rekeningRepository.save(chekIdRekening);
            return templateResponse.templateSukses(doSave);
        } catch (Exception e) {
            return templateResponse.templateEror(e);
        }
    }

    @Override
    public Map delete(Long rekening) {
        try {
            if (templateResponse.chekNull(rekening)) {
                return templateResponse.templateEror("Id Rekening is required");
            }
            Rekening checkIdRekening = rekeningRepository.getbyID(rekening);
            if (templateResponse.chekNull(checkIdRekening)) {
                return templateResponse.templateEror("Id Rekening Not found");
            }

//            2. update , tanggal deleted saja
            checkIdRekening.setDeleted_date(new Date());//
            rekeningRepository.save(checkIdRekening);

            return templateResponse.templateSukses("sukses deleted");

        } catch (Exception e) {
            return templateResponse.templateEror(e);
        }
    }
}
