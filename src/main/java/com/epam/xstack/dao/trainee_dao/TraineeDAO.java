package com.epam.xstack.dao.trainee_dao;

import com.epam.xstack.models.dto.trainee_dto.request.TraineeActivateDeActivateDTO;
import com.epam.xstack.models.dto.trainee_dto.request.TraineeProfileSelectRequestDTO;
import com.epam.xstack.models.dto.trainee_dto.request.TraineeRegistrationRequestDTO;
import com.epam.xstack.models.dto.trainee_dto.request.TraineeProfileUpdateRequestDTO;
import com.epam.xstack.models.dto.trainee_dto.response.TraineeOkResponseDTO;
import com.epam.xstack.models.dto.trainee_dto.response.TraineeProfileSelectResponseDTO;
import com.epam.xstack.models.dto.trainee_dto.response.TraineeRegistrationResponseDTO;
import com.epam.xstack.models.dto.trainee_dto.response.TraineeProfileUpdateResponseDTO;

import java.util.UUID;

public interface TraineeDAO {
    TraineeRegistrationResponseDTO saveTrainee(TraineeRegistrationRequestDTO requestDTO);
    TraineeProfileSelectResponseDTO selectTraineeProfileByUserName(UUID id, TraineeProfileSelectRequestDTO requestDTO);
    TraineeProfileUpdateResponseDTO updateTraineeProfile(UUID id, TraineeProfileUpdateRequestDTO requestDTO);
    TraineeOkResponseDTO activateDe_ActivateTrainee(UUID id, TraineeActivateDeActivateDTO dto);
}
