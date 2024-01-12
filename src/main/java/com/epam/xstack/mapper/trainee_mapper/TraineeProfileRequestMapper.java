package com.epam.xstack.mapper.trainee_mapper;

import com.epam.xstack.models.dto.trainee_dto.request.TraineeProfileRequestDTO;
import com.epam.xstack.models.entity.Trainee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TraineeProfileRequestMapper {
    TraineeProfileRequestDTO toDto(Trainee trainee);

    Trainee toEntity(TraineeProfileRequestDTO requestDTO);

    List<TraineeProfileRequestDTO> toDtos(List<Trainee> trainees);


    List<Trainee> toEntities(List<TraineeProfileRequestDTO> requestDTOS);
}