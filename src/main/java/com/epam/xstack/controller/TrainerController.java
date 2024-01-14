package com.epam.xstack.controller;

import com.epam.xstack.dao.trainer_dao.TrainerDAO;
import com.epam.xstack.models.dto.trainer_dto.request.*;
import com.epam.xstack.models.dto.trainer_dto.response.*;
import com.epam.xstack.service.trainer_service.TrainerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/trainers")
@RequiredArgsConstructor
@Api(tags = "Trainer controller")
public class TrainerController {
    private final TrainerService trainerService;

    @ApiOperation(value = "Save Trainer to database")
    @PostMapping("/save")
    public ResponseEntity<TrainerRegistrationResponseDTO> saveTrainee(@RequestBody TrainerRegistrationRequestDTO requestDTO) {
        return new ResponseEntity<>(trainerService.saveTrainer(requestDTO), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Select all Trainer from database")
    @GetMapping("/{id}")
    public ResponseEntity<TrainerProfileSelectResponseDTO> selectTrainerProfile(@PathVariable("id") UUID id, @RequestBody TrainerProfileSelectRequestDTO requestDTO) {
        return new ResponseEntity<>(trainerService.selectTrainerProfileByUserName(id, requestDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Update Trainer in database")
    @PutMapping("/update/{id}")
    public ResponseEntity<TrainerProfileUpdateResponseDTO> updateUser(@PathVariable("id") UUID id, @RequestBody TrainerProfileUpdateRequestDTO requestDTO) {
        return new ResponseEntity<>(trainerService.updateTrainerProfile(id, requestDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Update active or de active Trainer")
    @PatchMapping("/{id}")
    public ResponseEntity<TrainerOkResponseDTO> updateActivateDe_ActivateTrainer(@PathVariable("id") UUID id, @RequestBody TrainerActivateDeActivateDTO dto) {
        return new ResponseEntity<>(trainerService.activateDe_ActivateTrainer(id, dto), HttpStatus.OK);
    }

    private final TrainerDAO trainerDAO;

    @GetMapping("/select/{id}")
    public ResponseEntity<TrainerTrainingsListResponseDTO> select(@PathVariable("id") UUID id, @RequestBody TrainerTrainingsListRequestDTO requestDTO) {
        return new ResponseEntity<>(trainerService.selectTrainerTrainingsList(id, requestDTO), HttpStatus.OK);
    }
}
