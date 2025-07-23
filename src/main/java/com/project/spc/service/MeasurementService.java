package com.project.spc.service;

import com.project.spc.DTOs.MeasurementRequestDTO;
import com.project.spc.models.InspectionDimension;
import com.project.spc.models.InspectionPlan;
import com.project.spc.models.Measurement;
import com.project.spc.models.Part;
import com.project.spc.repository.MeasurementRepository;
import com.project.spc.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MeasurementService {

    @Autowired
    PartRepository partRepository;

    @Autowired
    MeasurementRepository measurementRepository;

    public void saveMeasurement(MeasurementRequestDTO measurementRequestDTO) {
        Part part = partRepository.findById(measurementRequestDTO.getPartNumber())
                .orElseThrow(() -> new RuntimeException("No existe ese numero de parte"));

        InspectionPlan inspectionPlan = part.getInspectionPlans().stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se encontró un plan para ese numero de parte"));

        for (MeasurementRequestDTO.MeasuredValueDTO m: measurementRequestDTO.getMeasurements()) {
            InspectionDimension dimension = inspectionPlan.getDimensions().stream()
                    .filter(d -> d.getName().equalsIgnoreCase(m.getDimensionName()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Dimension " + m.getDimensionName() + " no encontrada"));

            Measurement measurement =  new Measurement();
            measurement.setWorkOrder(measurementRequestDTO.getWorkOrder());
            measurement.setOperator(measurementRequestDTO.getOperator());
            measurement.setValue(m.getValue());
            measurement.setSpecification(dimension);
            measurement.setTimestamp(LocalDateTime.now());

            if (m.getValue() < dimension.getLowerLimit() || m.getValue() > dimension.getUpperLimit()) {
                System.out.println("Valor fuera de especificación " + m.getDimensionName());
            }

            measurementRepository.save(measurement);
        }
    }
}
