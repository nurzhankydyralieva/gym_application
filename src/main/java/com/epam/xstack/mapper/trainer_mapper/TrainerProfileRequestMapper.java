package com.epam.xstack.mapper.trainer_mapper;

import com.epam.xstack.models.dto.trainer_dto.request.TrainerProfileRequestDTO;
import com.epam.xstack.models.entity.Trainer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TrainerProfileRequestMapper {
    TrainerProfileRequestDTO toDto(Trainer trainer);

    Trainer toEntity(TrainerProfileRequestDTO requestDTO);

    List<TrainerProfileRequestDTO> toDtos(List<Trainer> trainers);

    List<Trainer> toEntities(List<TrainerProfileRequestDTO> requestDTOS);
}