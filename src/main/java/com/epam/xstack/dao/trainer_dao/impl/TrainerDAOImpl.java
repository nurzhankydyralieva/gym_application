package com.epam.xstack.dao.trainer_dao.impl;

import com.epam.xstack.dao.trainer_dao.TrainerDAO;
import com.epam.xstack.mapper.trainee_mapper.TraineeMapper;
import com.epam.xstack.mapper.trainer_mapper.TrainerActivateDeActivateMapper;
import com.epam.xstack.mapper.trainer_mapper.TrainerProfileSelectRequestMapper;
import com.epam.xstack.mapper.trainer_mapper.TrainerRegistrationRequestMapper;
import com.epam.xstack.mapper.trainer_mapper.TrainerProfileUpdateRequestMapper;
import com.epam.xstack.models.dto.trainer_dto.request.TrainerActivateDeActivateDTO;
import com.epam.xstack.models.dto.trainer_dto.request.TrainerProfileSelectRequestDTO;
import com.epam.xstack.models.dto.trainer_dto.request.TrainerRegistrationRequestDTO;
import com.epam.xstack.models.dto.trainer_dto.request.TrainerProfileUpdateRequestDTO;
import com.epam.xstack.models.dto.trainer_dto.response.TrainerOkResponseDTO;
import com.epam.xstack.models.dto.trainer_dto.response.TrainerProfileSelectResponseDTO;
import com.epam.xstack.models.dto.trainer_dto.response.TrainerRegistrationResponseDTO;
import com.epam.xstack.models.dto.trainer_dto.response.TrainerProfileUpdateResponseDTO;
import com.epam.xstack.models.entity.Trainer;
import com.epam.xstack.models.enums.Code;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TrainerDAOImpl implements TrainerDAO {
    private final SessionFactory sessionFactory;
    private final TrainerRegistrationRequestMapper registrationRequestMapper;
    private final TrainerProfileSelectRequestMapper getTrainerProfileRequestMapper;
    private final TrainerProfileUpdateRequestMapper updateTrainerProfileRequestMapper;
    private final TrainerActivateDeActivateMapper activateDeActivateTrainerMapper;

    @Override
    @Transactional
    public TrainerOkResponseDTO activateDe_ActivateTrainer(UUID id, TrainerActivateDeActivateDTO dto) {
        Session session = sessionFactory.getCurrentSession();
        Trainer trainer = activateDeActivateTrainerMapper.toEntity(dto);
        Trainer existingTrainer = session.get(Trainer.class, id);

        if (existingTrainer.getId() != null) {
            existingTrainer.setUserName(trainer.getUserName());
            existingTrainer.setIsActive(trainer.getIsActive());
            session.update(existingTrainer);
            activateDeActivateTrainerMapper.toDto(trainer);
            return TrainerOkResponseDTO
                    .builder()
                    .code(Code.STATUS_200_OK)
                    .response("Activate DeActivate Trainer updated")
                    .build();
        }else {
            throw new RuntimeException("Not available");
        }
    }
    @Override
    @Transactional
    public TrainerProfileUpdateResponseDTO updateTrainerProfile(UUID id, TrainerProfileUpdateRequestDTO requestDTO) {
        Session session = sessionFactory.getCurrentSession();
        Trainer trainer = updateTrainerProfileRequestMapper.toEntity(requestDTO);
        Trainer trainerToBeUpdated = session.get(Trainer.class, id);

        trainerToBeUpdated.setUserName(trainer.getUserName());
        trainerToBeUpdated.setFirstName(trainer.getFirstName());
        trainerToBeUpdated.setLastName(trainer.getLastName());
        trainerToBeUpdated.setIsActive(trainer.getIsActive());

        session.update(trainerToBeUpdated);

        updateTrainerProfileRequestMapper.toDto(trainer);
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
    public TrainerProfileSelectResponseDTO selectTrainerProfileByUserName(UUID id, TrainerProfileSelectRequestDTO requestDTO) {
        Session session = sessionFactory.getCurrentSession();
        Trainer trainer = getTrainerProfileRequestMapper.toEntity(requestDTO);
        Trainer trainerId = session.get(Trainer.class, id);

        if (trainerId.getUserName().equals(trainer.getUserName())) {
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
    public TrainerRegistrationResponseDTO saveTrainer(TrainerRegistrationRequestDTO requestDTO) {
        Session session = sessionFactory.getCurrentSession();

        Trainer trainer = registrationRequestMapper.toEntity(requestDTO);
        session.save(trainer);
        TrainerRegistrationRequestDTO newTrainer = registrationRequestMapper.toDto(trainer);
        String password = generateRandomPassword(10);
        trainer.setUserName(newTrainer.getFirstName() + "." + newTrainer.getLastName());
        trainer.setPassword(password);
        session.save(trainer);

        return TrainerRegistrationResponseDTO
                .builder()
                .userName(newTrainer.getFirstName() + "." + newTrainer.getLastName())
                .password(password)
                .build();
    }


    private static String generateRandomPassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_-+=<>?";
        Random random = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }
        return password.toString();
    }
}
