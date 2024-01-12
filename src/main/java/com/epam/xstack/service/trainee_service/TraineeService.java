package com.epam.xstack.service.trainee_service;

import com.epam.xstack.models.dto.trainee_dto.request.TraineeProfileRequestDTO;
import com.epam.xstack.models.dto.trainee_dto.request.TraineeRegistrationRequestDTO;
import com.epam.xstack.models.dto.trainee_dto.response.TraineeProfileResponseDTO;
import com.epam.xstack.models.dto.trainee_dto.response.TraineeRegistrationResponseDTO;

import java.util.UUID;

public interface TraineeService {
    TraineeRegistrationResponseDTO saveTrainee(TraineeRegistrationRequestDTO requestDTO);
    TraineeProfileResponseDTO selectTraineeProfileByUserName(UUID id, TraineeProfileRequestDTO requestDTO);
}
