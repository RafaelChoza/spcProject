package com.project.spc.service;

import com.project.spc.DTOs.InspectionPlanDto;
import com.project.spc.models.InspectionDimension;
import com.project.spc.models.InspectionPlan;
import com.project.spc.models.Part;
import com.project.spc.repository.InspectionDimensionRepository;
import com.project.spc.repository.InspectionPlanRepository;
import com.project.spc.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
public class InspectionPlanService {

    @Autowired
    PartRepository partRepository;

    @Autowired
    InspectionPlanRepository inspectionPlanRepository;

    @Autowired
    InspectionDimensionRepository inspectionDimensionRepository;

    public InspectionPlan creatInspectionPlan(InspectionPlanDto dto) {
        Part part = partRepository.findById(dto.getPartNumber())
                .orElseThrow(() -> new RuntimeException("No se encuentra el numero de parte"));

        InspectionPlan plan =  new InspectionPlan();
        plan.setPart(part);
        plan.setVersion(dto.getVersion());

        List<InspectionDimension> dimensions = dto.getDimensions().stream().map(d -> {
            InspectionDimension dim = new InspectionDimension();
            dim.setName(d.getName());
            dim.setLowerLimit(d.getLowerLimit());
            dim.setUpperLimit(d.getUpperLimit());
            dim.setPlan(plan);
            return  dim;
        }).toList();

        plan.setDimensions(dimensions);
        return inspectionPlanRepository.save(plan);
    }
}
