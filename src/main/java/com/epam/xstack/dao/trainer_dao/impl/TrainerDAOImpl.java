package com.epam.xstack.dao.trainer_dao.impl;

import com.epam.xstack.aspects.trainer_aspects.annotations.*;
import com.epam.xstack.dao.trainer_dao.TrainerDAO;
import com.epam.xstack.mapper.trainee_mapper.TraineeMapper;
import com.epam.xstack.mapper.trainer_mapper.*;
import com.epam.xstack.mapper.training_mapper.TrainingListMapper;
import com.epam.xstack.models.dto.trainer_dto.request.*;
import com.epam.xstack.models.dto.trainer_dto.response.*;
import com.epam.xstack.models.entity.Trainer;
import com.epam.xstack.models.enums.Code;
import com.epam.xstack.validation.generator.Generator;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TrainerDAOImpl implements TrainerDAO {
    private final SessionFactory sessionFactory;
    private final TrainerRegistrationRequestMapper registrationRequestMapper;
    private final TrainerProfileSelectRequestMapper getTrainerProfileRequestMapper;
    private final TrainerProfileUpdateRequestMapper updateTrainerProfileRequestMapper;
    private final TrainerActivateDeActivateMapper activateDeActivateTrainerMapper;
    private final TrainerTrainingsListMapper trainerTrainingsListMapper;
    private final Generator generator;


    @Override
    @Transactional
    @SelectTrainerTrainingsListAspectAnnotation
    public TrainerTrainingsListResponseDTO selectTrainerTrainingsList(UUID id, TrainerTrainingsListRequestDTO requestDTO) {
        Session session = sessionFactory.getCurrentSession();
        Trainer trainerId = session.get(Trainer.class, id);
        trainerTrainingsListMapper.toEntity(requestDTO);

        if (trainerId != null) {
            return TrainerTrainingsListResponseDTO
                    .builder()
                    .trainings(TrainingListMapper.INSTANCE.toDtos(trainerId.getTrainings()))
                    .build();
        } else {
            throw new RuntimeException("Not exists");
        }

    }

    @Override
    @Transactional
    @ActivateDe_ActivateTrainerAspectAnnotation
    public TrainerOkResponseDTO activateDe_ActivateTrainer(UUID id, TrainerActivateDeActivateDTO dto) {
        Session session = sessionFactory.getCurrentSession();
        Trainer trainer = activateDeActivateTrainerMapper.toEntity(dto);
        Trainer existingTrainer = session.get(Trainer.class, id);

        if (existingTrainer.getUserName().equals(dto.getUserName())) {
            existingTrainer.setIsActive(dto.getIsActive());
            session.update(existingTrainer);
            activateDeActivateTrainerMapper.toDto(trainer);
            return TrainerOkResponseDTO
                    .builder()
                    .code(Code.STATUS_200_OK)
                    .response("Activate DeActivate Trainer updated")
                    .build();
        } else {
            throw new RuntimeException("Not available");
        }
    }


    @Override
    @Transactional
    @UpdateTrainerProfileAspectAnnotation
    public TrainerProfileUpdateResponseDTO updateTrainerProfile(UUID id, TrainerProfileUpdateRequestDTO requestDTO) {
        Session session = sessionFactory.getCurrentSession();
        Trainer trainer = updateTrainerProfileRequestMapper.toEntity(requestDTO);
        Trainer trainerToBeUpdated = session.get(Trainer.class, id);
        if (trainerToBeUpdated.getId() == id) {
            trainerToBeUpdated.setFirstName(requestDTO.getFirstName());
            trainerToBeUpdated.setLastName(requestDTO.getLastName());
            trainerToBeUpdated.setIsActive(requestDTO.getIsActive());

            session.update(trainerToBeUpdated);
            updateTrainerProfileRequestMapper.toDto(trainer);
        }

        return TrainerProfileUpdateResponseDTO
                .builder()
                .userName(trainerToBeUpdated.getUserName())
                .firstName(trainerToBeUpdated.getFirstName())
                .lastName(trainerToBeUpdated.getLastName())
                .specialization(trainerToBeUpdated.getSpecialization())
                .isActive(trainerToBeUpdated.getIsActive())
                .trainees(TraineeMapper.INSTANCE.toDtos(trainerToBeUpdated.getTraineeList()))
                .build();
    }


    @Override
    @Transactional
    @SelectTrainerProfileByUserNameAspectAnnotation
    public TrainerProfileSelectResponseDTO selectTrainerProfileByUserName(UUID id, TrainerProfileSelectRequestDTO requestDTO) {
        Session session = sessionFactory.getCurrentSession();
        Trainer trainer = getTrainerProfileRequestMapper.toEntity(requestDTO);
        Trainer trainerId = session.get(Trainer.class, id);

        if (trainerId.getUserName().equals(requestDTO.getUserName())) {
            getTrainerProfileRequestMapper.toDto(trainer);

            return TrainerProfileSelectResponseDTO
                    .builder()
                    .firstName(trainerId.getFirstName())
                    .lastName(trainerId.getLastName())
                    .specialization(trainerId.getSpecialization())
                    .isActive(trainerId.getIsActive())
                    .traineeList(TraineeMapper.INSTANCE.toDtos(trainerId.getTraineeList()))
                    .build();
        } else {
            throw new RuntimeException("Not available");
        }

    }

    @Override
    @Transactional
    @SaveTrainerAspectAnnotation
    public TrainerRegistrationResponseDTO saveTrainer(TrainerRegistrationRequestDTO requestDTO) {
        Session session = sessionFactory.getCurrentSession();
        Trainer trainer = registrationRequestMapper.toEntity(requestDTO);
        String password = generator.generateRandomPassword();
        String createdUserName = generator.generateUserName(requestDTO.getFirstName(), requestDTO.getLastName());

        trainer.setUserName(createdUserName);
        trainer.setFirstName(requestDTO.getFirstName());
        trainer.setLastName(requestDTO.getLastName());
        trainer.setPassword(password);
        trainer.setIsActive(true);
        session.save(trainer);

        return TrainerRegistrationResponseDTO
                .builder()
                .userName(trainer.getUserName())
                .password(password)
                .build();
    }

}
