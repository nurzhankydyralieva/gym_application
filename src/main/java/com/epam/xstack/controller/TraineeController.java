package com.epam.xstack.controller;

import com.epam.xstack.dao.trainee_dao.impl.TraineeDAOImpl;
import com.epam.xstack.models.dto.trainee_dto.request.*;
import com.epam.xstack.models.dto.trainee_dto.response.*;
import com.epam.xstack.models.entity.Trainee;
import com.epam.xstack.service.trainee_service.TraineeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/trainees")
@RequiredArgsConstructor
@Api(tags = "Trainee controller")
public class TraineeController {
    private final TraineeService traineeService;

    @ApiOperation(value = "Save Trainee to database")
    @PostMapping("/save")
    public ResponseEntity<TraineeRegistrationResponseDTO> saveTrainee(@Valid @RequestBody TraineeRegistrationRequestDTO requestDTO) {
        return new ResponseEntity<>(traineeService.saveTrainee(requestDTO), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get Trainee by user name")
    @GetMapping("/{id}")
    public ResponseEntity<TraineeProfileSelectResponseDTO> selectTraineeProfile(@PathVariable("id") UUID id, @Valid @RequestBody TraineeProfileSelectRequestDTO requestDTO) {
        return new ResponseEntity<>(traineeService.selectTraineeProfileByUserName(id, requestDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Update Trainee in database")
    @PutMapping("/update/{id}")
    public ResponseEntity<TraineeProfileUpdateResponseDTO> updateUser(@PathVariable("id") UUID id, @Valid @RequestBody TraineeProfileUpdateRequestDTO requestDTO) {
        return new ResponseEntity<>(traineeService.updateTraineeProfile(id, requestDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Update Trainee's Trainer List in database")
    @PutMapping("/update-list/{id}")
    public ResponseEntity<TraineesTrainerListUpdateResponseDTO> updateTraineesTrainerList(@PathVariable("id") UUID id, @Valid @RequestBody TraineesTrainerListUpdateRequestDTO requestDTO) {
        return new ResponseEntity<>(traineeService.updateTraineesTrainerList(id, requestDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Update active or de active Trainee")
    @PatchMapping("/{id}")
    public ResponseEntity<TraineeOkResponseDTO> updateActivateDe_ActivateTrainer(@PathVariable("id") UUID id, @Valid @RequestBody TraineeActivateDeActivateDTO dto) {
        return new ResponseEntity<>(traineeService.activateDe_ActivateTrainee(id, dto), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Trainee by user name")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TraineeOkResponseDTO> deleteTraineeByUserName(@PathVariable("id") UUID id, @Valid @RequestBody TraineeProfileSelectRequestDTO requestDTO) {
        return new ResponseEntity<>(traineeService.deleteTraineeByUserName(id, requestDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Get Trainee Trainings List")
    @GetMapping("/select/{id}")
    public ResponseEntity<TraineeTrainingsListResponseDTO> select(@PathVariable("id") UUID id, @Valid @RequestBody TraineeTrainingsListRequestDTO requestDTO) {
        return new ResponseEntity<>(traineeService.selectTraineeTrainingsList(id, requestDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Get not assigned on trainee active trainers.")
    @GetMapping("/active-not-assigned/{id}")
    public ResponseEntity<TraineesTrainerActiveAndNotAssignedResponseDTO> selectNotAssignedOnTraineeActiveTrainers(@PathVariable("id") UUID id, @Valid @RequestBody TraineesTrainerActiveAndNotAssignedRequestDTO userName) {
        return new ResponseEntity<>(traineeService.selectNotAssignedOnTraineeActiveTrainers(id, userName), HttpStatus.OK);
    }

}
