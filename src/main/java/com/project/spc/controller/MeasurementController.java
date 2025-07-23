package com.project.spc.controller;

import com.project.spc.DTOs.MeasurementDetailDTO;
import com.project.spc.DTOs.MeasurementRequestDTO;
import com.project.spc.service.MeasurementService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/measurements")
public class MeasurementController {

    @Autowired
    MeasurementService measurementService;

    @PostMapping
    public ResponseEntity<Void> receiveMeasurement(@RequestBody MeasurementRequestDTO measurementRequestDTO) {
        measurementService.saveMeasurement(measurementRequestDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/allMeasurements")
    public ResponseEntity<List<MeasurementRequestDTO>> allMeasurements() {
        List<MeasurementRequestDTO> allmeasurements = measurementService.getAllMeasurements();

        return ResponseEntity.ok(allmeasurements);
    }

    @GetMapping("/details")
    public ResponseEntity<List<MeasurementDetailDTO>> getMeasurementDetails() {
        List<MeasurementDetailDTO> details = measurementService.getAllMeasurementDetails();
        return ResponseEntity.ok(details);
    }

}
