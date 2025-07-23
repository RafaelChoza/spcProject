package com.project.spc.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Part {
    @Id
    private String partNumber;
    private String description;

    @OneToMany(mappedBy = "part", cascade = CascadeType.ALL)
    private List<InspectionPlan> inspectionPlans;
}
