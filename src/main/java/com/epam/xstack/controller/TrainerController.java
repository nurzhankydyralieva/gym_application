package com.epam.xstack.controller;

import com.epam.xstack.models.dto.trainer_dto.request.*;
import com.epam.xstack.models.dto.trainer_dto.response.*;
import com.epam.xstack.service.trainer_service.TrainerService;
import com.epam.xstack.validation.NotNullValidation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/trainers")
@RequiredArgsConstructor
@Api(tags = "Trainer controller")
public class TrainerController {
    private final TrainerService trainerService;
    private final NotNullValidation validation;

    @ApiOperation(value = "Save Trainer to database")
    @PostMapping("/save")
    public ResponseEntity<TrainerRegistrationResponseDTO> saveTrainee(@Valid @RequestBody TrainerRegistrationRequestDTO requestDTO, BindingResult result) {
        validation.nullValidation(result);
        return new ResponseEntity<>(trainerService.saveTrainer(requestDTO), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get Trainer by user name")
    @GetMapping("/{id}")
    public ResponseEntity<TrainerProfileSelectResponseDTO> selectTrainerProfile(@PathVariable("id") UUID id, @Valid @RequestBody TrainerProfileSelectRequestDTO requestDTO, BindingResult result) {
        validation.userNotNullValidation(result);
        return new ResponseEntity<>(trainerService.selectTrainerProfileByUserName(id, requestDTO), HttpStatus.OK);
    }


    @ApiOperation(value = "Update Trainer in database")
    @PutMapping("/update/{id}")
    public ResponseEntity<TrainerProfileUpdateResponseDTO> updateUser(@PathVariable("id") UUID id, @Valid @RequestBody TrainerProfileUpdateRequestDTO requestDTO, BindingResult result) {
        validation.nullValidation(result);
        return new ResponseEntity<>(trainerService.updateTrainerProfile(id, requestDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Update active or de active Trainer")
    @PatchMapping("/{id}")
    public ResponseEntity<TrainerOkResponseDTO> updateActivateDe_ActivateTrainer(@PathVariable("id") UUID id, @Valid @RequestBody TrainerActivateDeActivateDTO dto, BindingResult result) {
        validation.nullValidation(result);
        return new ResponseEntity<>(trainerService.activateDe_ActivateTrainer(id, dto), HttpStatus.OK);
    }

    @ApiOperation(value = "Get Trainer Trainings List")
    @GetMapping("/select/{id}")
    public ResponseEntity<TrainerTrainingsListResponseDTO> select(@PathVariable("id") UUID id, @Valid @RequestBody TrainerTrainingsListRequestDTO requestDTO, BindingResult result) {
        validation.userNotNullValidation(result);
        return new ResponseEntity<>(trainerService.selectTrainerTrainingsList(id, requestDTO), HttpStatus.OK);
    }
}
