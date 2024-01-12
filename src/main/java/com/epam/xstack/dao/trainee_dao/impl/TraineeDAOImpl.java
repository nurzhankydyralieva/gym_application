package com.epam.xstack.dao.trainee_dao.impl;

import com.epam.xstack.dao.trainee_dao.TraineeDAO;
import com.epam.xstack.mapper.trainee_mapper.TraineeProfileRequestMapper;
import com.epam.xstack.mapper.trainee_mapper.TraineeRegistrationRequestMapper;
import com.epam.xstack.mapper.trainer_mapper.TrainerMapper;
import com.epam.xstack.models.dto.trainee_dto.request.TraineeProfileRequestDTO;
import com.epam.xstack.models.dto.trainee_dto.request.TraineeRegistrationRequestDTO;
import com.epam.xstack.models.dto.trainee_dto.response.TraineeProfileResponseDTO;
import com.epam.xstack.models.dto.trainee_dto.response.TraineeRegistrationResponseDTO;
import com.epam.xstack.models.entity.Trainee;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TraineeDAOImpl implements TraineeDAO {
    private final SessionFactory sessionFactory;
    private final TraineeRegistrationRequestMapper registrationRequestMapper;
    private final TraineeProfileRequestMapper getTraineeProfileRequestMapper;

    @Override
    @Transactional
    public TraineeProfileResponseDTO selectTraineeProfileByUserName(UUID id, TraineeProfileRequestDTO requestDTO) {
        Session session = sessionFactory.getCurrentSession();
        Trainee trainee = getTraineeProfileRequestMapper.toEntity(requestDTO);
        Trainee traineeId = session.get(Trainee.class, id);

        if (traineeId.getUserName().equals(trainee.getUserName())) {
            getTraineeProfileRequestMapper.toDto(trainee);

            return TraineeProfileResponseDTO
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
    @Override
    @Transactional
    public TraineeRegistrationResponseDTO saveTrainee(TraineeRegistrationRequestDTO requestDTO) {
        Session session = sessionFactory.getCurrentSession();
        Trainee trainee = registrationRequestMapper.toEntity(requestDTO);
        session.save(trainee);
        TraineeRegistrationRequestDTO newTrainee = registrationRequestMapper.toDto(trainee);
        String password = generateRandomPassword(10);
        trainee.setUserName(newTrainee.getFirstName() + "." + newTrainee.getLastName());
        trainee.setPassword(password);
        session.save(trainee);

        return TraineeRegistrationResponseDTO
                .builder()
                .userName(newTrainee.getFirstName() + "." + newTrainee.getLastName())
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
