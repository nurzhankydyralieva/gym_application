package com.epam.xstack.models.dto.training_dto.request;

import com.epam.xstack.models.entity.Trainee;
import com.epam.xstack.models.entity.Trainer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingSaveRequestDTO {
    private Trainee traineeUserName;
    private Trainer trainerUserName;
    private String trainingName;
    private Date trainingDate;
    private Number trainingDuration;
}
