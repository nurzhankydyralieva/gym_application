package com.epam.xstack.controller;

import com.epam.xstack.models.dto.trainee_dto.request.TraineeActivateDeActivateDTO;
import com.epam.xstack.models.dto.trainee_dto.request.TraineeProfileSelectRequestDTO;
import com.epam.xstack.models.dto.trainee_dto.request.TraineeProfileUpdateRequestDTO;
import com.epam.xstack.models.dto.trainee_dto.request.TraineeRegistrationRequestDTO;
import com.epam.xstack.models.dto.trainee_dto.response.TraineeOkResponseDTO;
import com.epam.xstack.models.dto.trainee_dto.response.TraineeProfileSelectResponseDTO;
import com.epam.xstack.models.dto.trainee_dto.response.TraineeProfileUpdateResponseDTO;
import com.epam.xstack.models.dto.trainee_dto.response.TraineeRegistrationResponseDTO;
import com.epam.xstack.service.trainee_service.TraineeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/trainees")
@RequiredArgsConstructor
@Api(tags = "Trainee controller")
public class TraineeController {
    private final TraineeService traineeService;

    @ApiOperation(value = "Save Trainee to database")
    @PostMapping("/save")
    public ResponseEntity<TraineeRegistrationResponseDTO> saveTrainee(@RequestBody TraineeRegistrationRequestDTO requestDTO) {
        return new ResponseEntity<>(traineeService.saveTrainee(requestDTO), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Select all Trainee from database")
    @GetMapping("/{id}")
    public ResponseEntity<TraineeProfileSelectResponseDTO> selectTraineeProfile(@PathVariable("id") UUID id, @RequestBody TraineeProfileSelectRequestDTO requestDTO) {
        return new ResponseEntity<>(traineeService.selectTraineeProfileByUserName(id, requestDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Update Trainee in database")
    @PutMapping("/update/{id}")
    public ResponseEntity<TraineeProfileUpdateResponseDTO> updateUser(@PathVariable("id") UUID id, @RequestBody TraineeProfileUpdateRequestDTO requestDTO) {
        return new ResponseEntity<>(traineeService.updateTraineeProfile(id, requestDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Update active or de active Trainee")
    @PatchMapping("/{id}")
    public ResponseEntity<TraineeOkResponseDTO> updateActivateDe_ActivateTrainer(@PathVariable("id") UUID id, @RequestBody TraineeActivateDeActivateDTO dto) {
        return new ResponseEntity<>(traineeService.activateDe_ActivateTrainee(id, dto), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Trainee by user name")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TraineeOkResponseDTO> deleteTraineeByUserName(@PathVariable("id") UUID id, @RequestBody TraineeProfileSelectRequestDTO requestDTO) {
        return new ResponseEntity<>(traineeService.deleteTraineeByUserName(id, requestDTO), HttpStatus.OK);
    }
}
