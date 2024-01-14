package com.epam.xstack.models.dto.training_dto.request;

import com.epam.xstack.models.dto.trainer_dto.response.TrainerDTO;
import com.epam.xstack.models.dto.training_type_dto.TrainingTypeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TraineeTrainingListDTO {
    private String trainingName;
    private Date trainingDate;
    private TrainingTypeDTO trainingType;
    private Number trainingDuration;
    private TrainerDTO trainer;
}
