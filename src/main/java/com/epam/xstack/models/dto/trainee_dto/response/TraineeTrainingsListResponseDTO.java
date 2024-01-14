package com.epam.xstack.models.dto.trainee_dto.response;

import com.epam.xstack.models.dto.training_dto.request.TraineeTrainingListDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TraineeTrainingsListResponseDTO {
    private List<TraineeTrainingListDTO> trainings;
}
