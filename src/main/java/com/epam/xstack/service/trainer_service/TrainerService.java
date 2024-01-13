package com.epam.xstack.service.trainer_service;

import com.epam.xstack.models.dto.trainer_dto.request.TrainerActivateDeActivateDTO;
import com.epam.xstack.models.dto.trainer_dto.request.TrainerProfileSelectRequestDTO;
import com.epam.xstack.models.dto.trainer_dto.request.TrainerRegistrationRequestDTO;
import com.epam.xstack.models.dto.trainer_dto.request.TrainerProfileUpdateRequestDTO;
import com.epam.xstack.models.dto.trainer_dto.response.TrainerOkResponseDTO;
import com.epam.xstack.models.dto.trainer_dto.response.TrainerProfileSelectResponseDTO;
import com.epam.xstack.models.dto.trainer_dto.response.TrainerRegistrationResponseDTO;
import com.epam.xstack.models.dto.trainer_dto.response.TrainerProfileUpdateResponseDTO;

import java.util.UUID;

public interface TrainerService {
    TrainerRegistrationResponseDTO saveTrainer(TrainerRegistrationRequestDTO requestDTO);
    TrainerProfileSelectResponseDTO selectTrainerProfileByUserName(UUID id, TrainerProfileSelectRequestDTO requestDTO);
    TrainerProfileUpdateResponseDTO updateTrainerProfile(UUID id, TrainerProfileUpdateRequestDTO requestDTO);
    TrainerOkResponseDTO activateDe_ActivateTrainer(UUID id, TrainerActivateDeActivateDTO dto);
}
