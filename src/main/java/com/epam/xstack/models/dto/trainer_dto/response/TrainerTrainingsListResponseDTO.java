package com.epam.xstack.models.dto.trainer_dto.response;

import com.epam.xstack.models.dto.training_dto.request.TrainingListDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainerTrainingsListResponseDTO {
    private List<TrainingListDTO> trainings;
}
