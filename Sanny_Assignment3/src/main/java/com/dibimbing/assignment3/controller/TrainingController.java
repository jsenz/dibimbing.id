package com.dibimbing.assignment3.controller;

import com.dibimbing.assignment3.model.Training;
import com.dibimbing.assignment3.repository.TrainingRepository;
import com.dibimbing.assignment3.service.TrainingService;
import com.dibimbing.assignment3.utils.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/training")
public class TrainingController {

    @Autowired
    public TrainingRepository trainingRepository;

    @Autowired
    public TrainingService trainingService;

    @Autowired
    public TemplateResponse templateResponse;

    @PostMapping("/save")
    public ResponseEntity<Map> save(@RequestBody Training objTraining){
        Map map = new HashMap();
        Map obj = trainingService.insert(objTraining);
        return new ResponseEntity<Map>(obj, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<Map> update(@RequestBody Training objTraining) {
        Map map = trainingService.update(objTraining);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Map>listByNama(@RequestParam() int page,
                                         @RequestParam() int size){
        Map list = trainingService.getAll(size, page);
        return new ResponseEntity<Map>(list, new HttpHeaders(), HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public ResponseEntity<Map>getById(@PathVariable(value = "id")Long id){
        Map dataKaryawan = trainingService.getById(id);
        return new ResponseEntity<Map>(dataKaryawan, new HttpHeaders(), HttpStatus.OK);

    }
}
