package com.epam.xstack.mapper.training_mapper;

import com.epam.xstack.models.dto.training_dto.request.TraineeTrainingListDTO;
import com.epam.xstack.models.entity.Training;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TraineeTrainingListMapper {
    TraineeTrainingListMapper INSTANCE = Mappers.getMapper(TraineeTrainingListMapper.class);

    TraineeTrainingListDTO toDto(Training training);

    Training toEntity(TraineeTrainingListDTO requestDTO);

    List<TraineeTrainingListDTO> toDtos(List<Training> trainings);

    List<Training> toEntities(List<TraineeTrainingListDTO> requestDTOS);
}
