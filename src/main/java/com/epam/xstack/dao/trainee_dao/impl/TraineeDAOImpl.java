package com.epam.xstack.dao.trainee_dao.impl;

import com.epam.xstack.aspects.trainee_aspects.annotations.ActivateDe_ActivateTraineeAspectAnnotation;
import com.epam.xstack.aspects.trainee_aspects.annotations.UpdateTraineeProfileAspectAnnotation;
import com.epam.xstack.aspects.trainee_aspects.annotations.UpdateTraineesTrainerListAspectAnnotation;
import com.epam.xstack.dao.trainee_dao.TraineeDAO;
import com.epam.xstack.mapper.trainee_mapper.*;
import com.epam.xstack.mapper.trainer_mapper.TrainerMapper;
import com.epam.xstack.mapper.training_mapper.TraineeTrainingMapper;
import com.epam.xstack.models.dto.trainee_dto.request.*;
import com.epam.xstack.models.dto.trainee_dto.response.*;
import com.epam.xstack.models.entity.Trainee;
import com.epam.xstack.models.enums.Code;
import com.epam.xstack.validation.CheckUserNameExistence;
import com.epam.xstack.validation.generator.Generator;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TraineeDAOImpl implements TraineeDAO {
    private final SessionFactory sessionFactory;
    private final TraineeRegistrationRequestMapper registrationRequestMapper;
    private final TraineeProfileSelectRequestMapper getTraineeProfileRequestMapper;
    private final TraineeProfileUpdateRequestMapper updateTraineeProfileRequestMapper;
    private final TraineeActivateDeActivateMapper activateDeActivateTraineeMapper;
    private final TraineeTrainingsListMapper traineeTrainingsListMapper;
    private final Generator generator;

    @Override
    @Transactional
    @ActivateDe_ActivateTraineeAspectAnnotation
    public TraineeOkResponseDTO activateDe_ActivateTrainee(UUID id, TraineeActivateDeActivateDTO dto) {
        Session session = sessionFactory.getCurrentSession();
        Trainee trainee = activateDeActivateTraineeMapper.toEntity(dto);
        Trainee existingTrainee = session.get(Trainee.class, id);

        if (existingTrainee.getUserName().equals(dto.getUserName())) {
            existingTrainee.setIsActive(dto.getIsActive());
            session.update(existingTrainee);
            activateDeActivateTraineeMapper.toDto(trainee);
            return TraineeOkResponseDTO
                    .builder()
                    .code(Code.STATUS_200_OK)
                    .response("Activate DeActivate Trainee updated")
                    .build();
        } else {
            throw new RuntimeException("Not available");
        }
    }

    private final TraineesTrainerListUpdateMapper traineesTrainerListUpdateMapper;

    //TODO update method is not working
    @Override
    @Transactional
    @UpdateTraineesTrainerListAspectAnnotation
    public TraineesTrainerListUpdateResponseDTO updateTraineesTrainerList(UUID id, TraineesTrainerListUpdateRequestDTO requestDTO) {
        Session session = sessionFactory.getCurrentSession();
        Trainee trainee = traineesTrainerListUpdateMapper.toEntity(requestDTO);
        Trainee traineeToBeUpdated = session.get(Trainee.class, id);

        if (traineeToBeUpdated.getId() == id) {
            traineeToBeUpdated.setTrainers(trainee.getTrainers());

            session.update(traineeToBeUpdated);
            traineesTrainerListUpdateMapper.toDto(trainee);
        }

        return TraineesTrainerListUpdateResponseDTO
                .builder()
                .trainers(TrainerMapper.INSTANCE.toDtos(traineeToBeUpdated.getTrainers()))
                .build();

    }

    @Override
    @Transactional
    @UpdateTraineeProfileAspectAnnotation
    public TraineeProfileUpdateResponseDTO updateTraineeProfile(UUID id, TraineeProfileUpdateRequestDTO requestDTO) {
        Session session = sessionFactory.getCurrentSession();
        Trainee trainee = updateTraineeProfileRequestMapper.toEntity(requestDTO);
        Trainee traineeToBeUpdated = session.get(Trainee.class, id);
        if (traineeToBeUpdated.getId() == id) {
            traineeToBeUpdated.setFirstName(trainee.getFirstName());
            traineeToBeUpdated.setLastName(trainee.getLastName());
            traineeToBeUpdated.setDateOfBirth(trainee.getDateOfBirth());
            traineeToBeUpdated.setAddress(trainee.getAddress());
            traineeToBeUpdated.setIsActive(trainee.getIsActive());

            session.update(traineeToBeUpdated);
            updateTraineeProfileRequestMapper.toDto(trainee);
        }

        return TraineeProfileUpdateResponseDTO
                .builder()
                .userName(traineeToBeUpdated.getUserName())
                .firstName(traineeToBeUpdated.getFirstName())
                .lastName(traineeToBeUpdated.getLastName())
                .dateOfBirth(traineeToBeUpdated.getDateOfBirth())
                .address(traineeToBeUpdated.getAddress())
                .isActive(traineeToBeUpdated.getIsActive())
                .trainers(TrainerMapper.INSTANCE.toDtos(traineeToBeUpdated.getTrainers()))
                .build();
    }

    @Override
    @Transactional
    public TraineeTrainingsListResponseDTO selectTraineeTrainingsList(UUID id, TraineeTrainingsListRequestDTO requestDTO) {
        Session session = sessionFactory.getCurrentSession();
        Trainee traineeId = session.get(Trainee.class, id);
        Trainee trainee = traineeTrainingsListMapper.toEntity(requestDTO);
        if (traineeId.getUserName().equals(trainee.getUserName())) {
            traineeTrainingsListMapper.toDto(trainee);
            return TraineeTrainingsListResponseDTO
                    .builder()
                    .trainings(TraineeTrainingMapper.INSTANCE.toDtos(traineeId.getTrainings()))
                    .build();
        } else {
            throw new RuntimeException("Not exists");
        }
    }

    @Override
    @Transactional
    public TraineeProfileSelectResponseDTO selectTraineeProfileByUserName(UUID id, TraineeProfileSelectRequestDTO requestDTO) {
        Session session = sessionFactory.getCurrentSession();
        Trainee trainee = getTraineeProfileRequestMapper.toEntity(requestDTO);
        Trainee traineeId = session.get(Trainee.class, id);

        if (traineeId.getUserName().equals(trainee.getUserName())) {
            getTraineeProfileRequestMapper.toDto(trainee);

            return TraineeProfileSelectResponseDTO
                    .builder()
                    .firstName(traineeId.getFirstName())
                    .lastName(traineeId.getLastName())
                    .address(traineeId.getAddress())
                    .isActive(traineeId.getIsActive())
                    .dateOfBirth(traineeId.getDateOfBirth())
                    .trainers(TrainerMapper.INSTANCE.toDtos(traineeId.getTrainers()))
                    .build();
        } else {
            throw new RuntimeException("Not available");
        }
    }

    private final CheckUserNameExistence checkUserNameExistence;

    @Override
    @Transactional
    public TraineeRegistrationResponseDTO saveTrainee(TraineeRegistrationRequestDTO requestDTO) {
        Session session = sessionFactory.getCurrentSession();
        Trainee trainee = registrationRequestMapper.toEntity(requestDTO);
        String password = generator.generateRandomPassword();
        String createdUserName = generator.generateUserName(trainee.getFirstName(), trainee.getLastName());

        checkUserNameExistence.userNameExists(createdUserName);

        trainee.setUserName(createdUserName);
        trainee.setPassword(password);
        trainee.setIsActive(true);
        session.save(trainee);

        return TraineeRegistrationResponseDTO
                .builder()
                .userName(trainee.getUserName())
                .password(password)
                .build();
    }


    @Override
    @Transactional
    public TraineeOkResponseDTO deleteTraineeByUserName(UUID id, TraineeProfileSelectRequestDTO requestDTO) {
        Session session = sessionFactory.getCurrentSession();
        Trainee trainee = getTraineeProfileRequestMapper.toEntity(requestDTO);
        Trainee traineeId = session.get(Trainee.class, id);

        if (traineeId.getUserName().equals(trainee.getUserName())) {
            session.remove(traineeId);
            return TraineeOkResponseDTO
                    .builder()
                    .response("Trainee is deleted from database")
                    .code(Code.STATUS_200_OK)
                    .build();
        } else {
            throw new RuntimeException("Trainee is not available in database");
        }

    }

    @Override
    @Transactional
    public TraineesTrainerActiveAndNotAssignedResponseDTO selectNotAssignedOnTraineeActiveTrainers(UUID id, TraineesTrainerActiveAndNotAssignedRequestDTO requestDTO) {
        Session session = sessionFactory.getCurrentSession();
        Trainee traineeUserName = session.get(Trainee.class, id);
        if (traineeUserName.getUserName().equals(requestDTO.getUserName()) && traineeUserName.getIsActive() && !traineeUserName.getIsAssigned()) {
            return TraineesTrainerActiveAndNotAssignedResponseDTO
                    .builder()
                    .trainers(TrainerMapper.INSTANCE.toDtos(traineeUserName.getTrainers()))
                    .build();
        } else {
            throw new RuntimeException("Not available");
        }
    }

}
