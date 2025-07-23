package com.project.spc.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "measurement")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double measuredValue;

    private LocalDateTime measurementTime = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "dimension_id")
    private InspectionDimension specification;

    private String operator;
    private String workOrder;
}
