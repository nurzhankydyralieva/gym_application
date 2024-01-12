package com.epam.xstack.service.trainer_service;

import com.epam.xstack.models.dto.trainer_dto.request.TrainerProfileRequestDTO;
import com.epam.xstack.models.dto.trainer_dto.request.TrainerRegistrationRequestDTO;
import com.epam.xstack.models.dto.trainer_dto.response.TrainerProfileResponseDTO;
import com.epam.xstack.models.dto.trainer_dto.response.TrainerRegistrationResponseDTO;

import java.util.UUID;

public interface TrainerService {
    TrainerRegistrationResponseDTO saveTrainer(TrainerRegistrationRequestDTO requestDTO);
    TrainerProfileResponseDTO selectTrainerProfileByUserName(UUID id, TrainerProfileRequestDTO requestDTO);
}