package com.project.spc.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InspectionPlanDto {
    private String partNumber;
    private String version;
    private List<DimensionDTO> dimensions;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DimensionDTO {
        private String name;
        private Double lowerLimit;
        private Double upperLimit;

    }
}
