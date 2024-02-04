package com.epam.xstack.mapper.trainee_mapper;

import com.epam.xstack.models.dto.trainee_dto.request.TraineesTrainerListUpdateRequestDTO;
import com.epam.xstack.models.entity.Trainee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TraineesTrainerListUpdateMapper {
    TraineesTrainerListUpdateMapper INSTANCE = Mappers.getMapper(TraineesTrainerListUpdateMapper.class);
    TraineesTrainerListUpdateRequestDTO toDto(Trainee trainee);

    Trainee toEntity(TraineesTrainerListUpdateRequestDTO requestDTO);

    List<TraineesTrainerListUpdateRequestDTO> toDtos(List<Trainee> trainees);

    List<Trainee> toEntities(List<TraineesTrainerListUpdateRequestDTO> requestDTOS);
}
