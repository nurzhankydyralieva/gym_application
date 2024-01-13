package com.epam.xstack.models.dto.training_type_dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingTypeDTO {

    private Long id;
    private String trainingType;
}
