package com.project.spc.controller;

import com.project.spc.DTOs.InspectionPlanDto;
import com.project.spc.models.InspectionPlan;
import com.project.spc.service.InspectionPlanService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/allPlans")
    public ResponseEntity<List<InspectionPlanDto>> getAllInspectionPlans() {
        List<InspectionPlanDto> allPlans = inspectionPlanService.getAllInspectionPlans();

        return ResponseEntity.ok(allPlans);
    }

    @GetMapping("/{partNumber}")
    public ResponseEntity<InspectionPlanDto> getInspectionPlan(@PathVariable String partNumber) {
        InspectionPlanDto inspectionPlan = inspectionPlanService.getInspectionPlanByPartNumber(partNumber);

        return ResponseEntity.ok(inspectionPlan);
    }


}
