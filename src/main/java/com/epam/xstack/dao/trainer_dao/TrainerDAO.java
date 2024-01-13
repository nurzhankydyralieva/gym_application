package com.epam.xstack.dao.trainer_dao;

import com.epam.xstack.models.dto.trainer_dto.request.TrainerProfileRequestDTO;
import com.epam.xstack.models.dto.trainer_dto.request.TrainerRegistrationRequestDTO;
import com.epam.xstack.models.dto.trainer_dto.request.TrainerProfileUpdateRequestDTO;
import com.epam.xstack.models.dto.trainer_dto.response.TrainerProfileResponseDTO;
import com.epam.xstack.models.dto.trainer_dto.response.TrainerRegistrationResponseDTO;
import com.epam.xstack.models.dto.trainer_dto.response.TrainerProfileUpdateResponseDTO;

import java.util.UUID;

public interface TrainerDAO {
    TrainerRegistrationResponseDTO saveTrainer(TrainerRegistrationRequestDTO requestDTO);
    TrainerProfileResponseDTO selectTrainerProfileByUserName(UUID id, TrainerProfileRequestDTO requestDTO);
    TrainerProfileUpdateResponseDTO updateTrainerProfile(UUID id, TrainerProfileUpdateRequestDTO requestDTO);
}
