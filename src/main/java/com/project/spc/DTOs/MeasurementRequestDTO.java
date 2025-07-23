package com.project.spc.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementRequestDTO {
    private String partNumber;
    private String workOrder;
    private String operator;
    private List<MeasuredValueDTO> measurements;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MeasuredValueDTO {
        private String dimensionName; // ejemplo: "Diámetro"
        private Double value;
    }
}

