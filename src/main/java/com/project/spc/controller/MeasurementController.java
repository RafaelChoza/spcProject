package com.project.spc.controller;

import com.project.spc.DTOs.MeasurementRequestDTO;
import com.project.spc.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
}
