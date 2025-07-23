package com.project.spc.service;

import com.project.spc.DTOs.MeasurementDetailDTO;
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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        for (MeasurementRequestDTO.MeasuredValueDTO m : measurementRequestDTO.getMeasurements()) {
            InspectionDimension dimension = inspectionPlan.getDimensions().stream()
                    .filter(d -> d.getName().equalsIgnoreCase(m.getDimensionName()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Dimension " + m.getDimensionName() + " no encontrada"));

            Measurement measurement = new Measurement();
            measurement.setWorkOrder(measurementRequestDTO.getWorkOrder());
            measurement.setOperator(measurementRequestDTO.getOperator());
            measurement.setMeasuredValue(m.getValue());
            measurement.setSpecification(dimension);
            measurement.setMeasurementTime(LocalDateTime.now());

            if (m.getValue() < dimension.getLowerLimit() || m.getValue() > dimension.getUpperLimit()) {
                System.out.println("Valor fuera de especificación " + m.getDimensionName());
            }

            measurementRepository.save(measurement);
        }
    }

    public List<MeasurementRequestDTO> getAllMeasurements() {
        List<Measurement> allMeasurements = measurementRepository.findAll();

        // Agrupar por combinación de partNumber, workOrder y operator
        Map<String, List<Measurement>> grouped = allMeasurements.stream()
                .collect(Collectors.groupingBy(m -> m.getSpecification().getPlan().getPart().getPartNumber() + "|" +
                        m.getWorkOrder() + "|" +
                        m.getOperator() + "|" +
                        m.getMeasurementTime().toString()));

        // Convertir cada grupo en un MeasurementRequestDTO
        return grouped.entrySet().stream().map(entry -> {
            String[] keys = entry.getKey().split("\\|");
            String partNumber = keys[0];
            String workOrder = keys[1];
            String operator = keys[2];
            LocalDateTime measurementTime = LocalDateTime.parse(keys[3]);

            List<MeasurementRequestDTO.MeasuredValueDTO> measuredValues = entry.getValue().stream().map(m -> {
                MeasurementRequestDTO.MeasuredValueDTO dto = new MeasurementRequestDTO.MeasuredValueDTO();
                dto.setDimensionName(m.getSpecification().getName());
                dto.setValue(m.getMeasuredValue()); // o m.getValue() si no lo has renombrado
                return dto;
            }).toList();

            return new MeasurementRequestDTO(partNumber, workOrder, operator, measurementTime, measuredValues);
        }).toList();
    }

    public List<MeasurementDetailDTO> getAllMeasurementDetails() {
        List<Measurement> allMeasurements = measurementRepository.findAll();

        return allMeasurements.stream().map(m -> {

            return new MeasurementDetailDTO(
                    m.getId(),
                    m.getMeasuredValue(),
                    m.getMeasurementTime(),
                    m.getOperator(),
                    m.getWorkOrder(),
                    m.getSpecification().getName(),
                    m.getSpecification().getLowerLimit(),
                    m.getSpecification().getUpperLimit());
        }).toList();
    }

}
