package com.epam.xstack.models.dto.trainer_dto.response;

import com.epam.xstack.models.entity.TrainingType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainerDTO {
    private String userName;
    private String firstName;
    private String lastName;
    private TrainingType specialization;
}
