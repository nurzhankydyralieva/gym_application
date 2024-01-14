package com.epam.xstack.models.dto.training_dto.request;

import com.epam.xstack.models.dto.trainee_dto.response.TraineeDTO;
import com.epam.xstack.models.dto.training_type_dto.TrainingTypeDTO;
import com.epam.xstack.models.entity.Trainee;
import com.epam.xstack.models.entity.TrainingType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingListDTO {
    private String trainingName;
    private Date trainingDate;
    private TrainingTypeDTO trainingType;
    private Number trainingDuration;
   private TraineeDTO trainee;

}
