package com.project.spc.controller;

import com.project.spc.DTOs.InspectionPlanDto;
import com.project.spc.models.InspectionPlan;
import com.project.spc.service.InspectionPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/plans")
public class InspectionPlanController {

    @Autowired
    InspectionPlanService inspectionPlanService;

    @PostMapping
    public ResponseEntity<InspectionPlan> createPlan(@RequestBody InspectionPlanDto dto) {
        InspectionPlan plan = inspectionPlanService.creatInspectionPlan(dto);

        return ResponseEntity.ok(plan);
    }
}
