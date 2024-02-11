package com.epam.xstack.controller;

import com.epam.xstack.models.dto.training_dto.request.TrainingSaveRequestDTO;
import com.epam.xstack.models.dto.training_dto.response.TrainingSaveResponseDTO;
import com.epam.xstack.service.training_service.TrainingService;
import com.epam.xstack.validation.NotNullValidation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trainings")
@RequiredArgsConstructor
@Api(tags = "Training controller")
public class TrainingController {
    private final TrainingService trainingService;
    private final NotNullValidation validation;

    @ApiOperation(value = "Save Training to database")
    @PostMapping("/save")
    public ResponseEntity<TrainingSaveResponseDTO> saveTraining(@RequestBody TrainingSaveRequestDTO requestDTO, BindingResult result) {
        validation.nullValidation(result);
        return new ResponseEntity<>(trainingService.saveTraining(requestDTO), HttpStatus.CREATED);
    }

}
