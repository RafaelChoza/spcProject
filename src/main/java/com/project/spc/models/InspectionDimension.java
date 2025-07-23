package com.project.spc.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InspectionDimension {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double lowerLimit;
    private Double upperLimit;

    @ManyToOne
    @JoinColumn(name="plan_id")
    private InspectionPlan plan;

    @OneToMany(mappedBy = "specification", cascade = CascadeType.ALL)
    private List<Measurement> measurements;
}
