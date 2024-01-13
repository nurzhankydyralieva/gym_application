package com.epam.xstack.service.trainer_service.impl;

import com.epam.xstack.dao.trainer_dao.TrainerDAO;
import com.epam.xstack.models.dto.trainer_dto.request.TrainerActivateDeActivateDTO;
import com.epam.xstack.models.dto.trainer_dto.request.TrainerProfileSelectRequestDTO;
import com.epam.xstack.models.dto.trainer_dto.request.TrainerProfileUpdateRequestDTO;
import com.epam.xstack.models.dto.trainer_dto.request.TrainerRegistrationRequestDTO;
import com.epam.xstack.models.dto.trainer_dto.response.TrainerOkResponseDTO;
import com.epam.xstack.models.dto.trainer_dto.response.TrainerProfileSelectResponseDTO;
import com.epam.xstack.models.dto.trainer_dto.response.TrainerProfileUpdateResponseDTO;
import com.epam.xstack.models.dto.trainer_dto.response.TrainerRegistrationResponseDTO;
import com.epam.xstack.service.trainer_service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrainerServiceImpl implements TrainerService {
    private final TrainerDAO trainerDAO;

    @Override
    public TrainerRegistrationResponseDTO saveTrainer(TrainerRegistrationRequestDTO requestDTO) {
        return trainerDAO.saveTrainer(requestDTO);
    }

    @Override
    public TrainerProfileSelectResponseDTO selectTrainerProfileByUserName(UUID id, TrainerProfileSelectRequestDTO requestDTO) {
        return trainerDAO.selectTrainerProfileByUserName(id, requestDTO);
    }

    @Override
    public TrainerProfileUpdateResponseDTO updateTrainerProfile(UUID id, TrainerProfileUpdateRequestDTO requestDTO) {
        return trainerDAO.updateTrainerProfile(id, requestDTO);
    }

    @Override
    public TrainerOkResponseDTO activateDe_ActivateTrainer(UUID id, TrainerActivateDeActivateDTO dto) {
        return trainerDAO.activateDe_ActivateTrainer(id, dto);
    }
}
