package com.epam.xstack.service.trainee_service.impl;

import com.epam.xstack.dao.trainee_dao.TraineeDAO;
import com.epam.xstack.models.dto.trainee_dto.request.TraineeActivateDeActivateDTO;
import com.epam.xstack.models.dto.trainee_dto.request.TraineeProfileSelectRequestDTO;
import com.epam.xstack.models.dto.trainee_dto.request.TraineeRegistrationRequestDTO;
import com.epam.xstack.models.dto.trainee_dto.request.TraineeProfileUpdateRequestDTO;
import com.epam.xstack.models.dto.trainee_dto.response.TraineeOkResponseDTO;
import com.epam.xstack.models.dto.trainee_dto.response.TraineeProfileSelectResponseDTO;
import com.epam.xstack.models.dto.trainee_dto.response.TraineeRegistrationResponseDTO;
import com.epam.xstack.models.dto.trainee_dto.response.TraineeProfileUpdateResponseDTO;
import com.epam.xstack.service.trainee_service.TraineeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TraineeServiceImpl implements TraineeService {
    private final TraineeDAO traineeDAO;

    @Override
    public TraineeRegistrationResponseDTO saveTrainee(TraineeRegistrationRequestDTO requestDTO) {
        return traineeDAO.saveTrainee(requestDTO);
    }
    @Override
    public TraineeProfileSelectResponseDTO selectTraineeProfileByUserName(UUID id, TraineeProfileSelectRequestDTO requestDTO) {
        return traineeDAO.selectTraineeProfileByUserName(id, requestDTO);
    }
    @Override
    public TraineeProfileUpdateResponseDTO updateTraineeProfile(UUID id, TraineeProfileUpdateRequestDTO requestDTO) {
        return traineeDAO.updateTraineeProfile(id, requestDTO);
    }
    @Override
    public TraineeOkResponseDTO activateDe_ActivateTrainee(UUID id, TraineeActivateDeActivateDTO dto) {
        return traineeDAO.activateDe_ActivateTrainee(id, dto);
    }
}
