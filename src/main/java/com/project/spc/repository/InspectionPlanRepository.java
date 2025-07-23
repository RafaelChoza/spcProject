package com.project.spc.repository;

import com.project.spc.models.InspectionPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InspectionPlanRepository extends JpaRepository<InspectionPlan, Long> {
}
