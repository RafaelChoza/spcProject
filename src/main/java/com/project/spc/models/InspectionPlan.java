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
public class InspectionPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String version;

    @ManyToOne
    @JoinColumn(name = "part_number")
    private Part part;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
    private List<InspectionDimension> dimensions;
}
