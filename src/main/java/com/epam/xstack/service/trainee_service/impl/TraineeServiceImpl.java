package com.epam.xstack.service.trainee_service.impl;

import com.epam.xstack.dao.trainee_dao.TraineeDAO;
import com.epam.xstack.models.dto.trainee_dto.request.TraineeProfileRequestDTO;
import com.epam.xstack.models.dto.trainee_dto.request.TraineeRegistrationRequestDTO;
import com.epam.xstack.models.dto.trainee_dto.response.TraineeProfileResponseDTO;
import com.epam.xstack.models.dto.trainee_dto.response.TraineeRegistrationResponseDTO;
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
    public TraineeProfileResponseDTO selectTraineeProfileByUserName(UUID id, TraineeProfileRequestDTO requestDTO) {
        return traineeDAO.selectTraineeProfileByUserName(id, requestDTO);
    }

}
