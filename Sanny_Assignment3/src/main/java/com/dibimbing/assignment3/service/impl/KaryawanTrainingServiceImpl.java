package com.dibimbing.assignment3.service.impl;

import com.dibimbing.assignment3.model.Karyawan;
import com.dibimbing.assignment3.model.KaryawanTraining;
import com.dibimbing.assignment3.model.Training;
import com.dibimbing.assignment3.repository.KaryawanRepository;
import com.dibimbing.assignment3.repository.KaryawanTrainingRepository;
import com.dibimbing.assignment3.repository.TrainingRepository;
import com.dibimbing.assignment3.service.KaryawanTrainingService;
import com.dibimbing.assignment3.utils.TemplateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;


@Service
public class KaryawanTrainingServiceImpl implements KaryawanTrainingService {

    @Autowired
    public KaryawanTrainingRepository karyawanTrainingRepository;

    @Autowired
    public KaryawanRepository karyawanRepository;

    @Autowired
    public TrainingRepository trainingRepository;

    @Autowired
    public TemplateResponse templateResponse;

    public static final Logger log = LoggerFactory.getLogger(KaryawanServiceImpl.class);

    @Override
    public Map insert(KaryawanTraining request) {

        try {

            if(templateResponse.chekNull(request.getTraining_date())){
                return   templateResponse.templateEror("training_date Tidak boleh kosong!!");
            }
//             Training chekIdtTraining = trainingRepository.getbyID(request.getTraining().getId());
//             if(templateResponse.chekNull(chekIdtTraining)){
//                 return   templateResponse.templateEror("Id Training Tidak boleh null");
//             }
            Karyawan chekIdKaryawan =  karyawanRepository.getbyIDKaryawan(request.getKaryawan().getId());
            if(templateResponse.chekNull(chekIdKaryawan)){
                return   templateResponse.templateEror("Id Karyawan Tidak ada di database");
            }

            Training chekIdTraining =  trainingRepository.getbyID(request.getTraining().getId());
            if(templateResponse.chekNull(chekIdTraining)){
                return   templateResponse.templateEror("Id Training Tidak ada di database");
            }

            //disimpan ke db: objek karyawan training
            KaryawanTraining objSave = new KaryawanTraining();
            objSave.setKaryawan(chekIdKaryawan);
            objSave.setTraining(chekIdTraining);
            objSave.setTraining_date(objSave.getTraining_date());
            KaryawanTraining doSave = karyawanTrainingRepository.save(request);
            return templateResponse.templateSukses(doSave);
        }catch (Exception e){
            return templateResponse.templateEror(e);
        }

    }

    @Override
    public Map delete(Long kry) {
        try {
            if (templateResponse.chekNull(kry)) {
                return templateResponse.templateEror("Id Karyawan Training is required");
            }

            KaryawanTraining chekIdKaryawanTraining = karyawanTrainingRepository.getbyID(kry);
            if (templateResponse.chekNull(chekIdKaryawanTraining)) {
                return templateResponse.templateEror("Id Karyawan Not found");
            }

            chekIdKaryawanTraining.setDeleted_date(new Date());//
            karyawanTrainingRepository.save(chekIdKaryawanTraining);

            return templateResponse.templateSukses("sukses deleted : \n"+karyawanTrainingRepository);

        } catch (Exception e) {
            return templateResponse.templateEror(e);
        }
    }
    @Override
    public Map getAll(int size, int page) {
        try {
            Pageable show_data = PageRequest.of(page, size);
            Page<KaryawanTraining> list = karyawanTrainingRepository.getAllData(show_data);
            return templateResponse.templateSukses(list);
        } catch (Exception e) {
            log.error("ada error di method getAll:" + e);
            return templateResponse.templateEror(e);
        }
    }

}
