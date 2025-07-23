package com.project.spc.DTOs;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementDetailDTO {
    private Long measurementId;
    private Double measuredValue;
    private LocalDateTime measurementTime;
    private String operator;
    private String workOrder;

    private String dimensionName;
    private Double lowerLimit;
    private Double upperLimit;
}
