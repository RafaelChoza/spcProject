package com.project.spc.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class InspectionPlanDto {
    private String partNumber;
    private String version;
    private List<DimensionDTO> dimensions;

    @Data
    public static class DimensionDTO {
        private String name;
        private Double lowerLimit;
        private Double upperLimit;

    }
}
