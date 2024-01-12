package com.epam.xstack.controller;

import com.epam.xstack.models.dto.trainee_dto.request.TraineeProfileRequestDTO;
import com.epam.xstack.models.dto.trainee_dto.request.TraineeRegistrationRequestDTO;
import com.epam.xstack.models.dto.trainee_dto.response.TraineeProfileResponseDTO;
import com.epam.xstack.models.dto.trainee_dto.response.TraineeRegistrationResponseDTO;
import com.epam.xstack.service.trainee_service.TraineeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/trainees")
@RequiredArgsConstructor
public class TraineeController {
    private final TraineeService traineeService;
    @PostMapping("/save")
    public ResponseEntity<TraineeRegistrationResponseDTO> saveTrainee(@RequestBody TraineeRegistrationRequestDTO requestDTO) {
        return new ResponseEntity<>(traineeService.saveTrainee(requestDTO), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TraineeProfileResponseDTO> selectTraineeProfile(@PathVariable("id") UUID id, @RequestBody TraineeProfileRequestDTO requestDTO) {
        return new ResponseEntity<>(traineeService.selectTraineeProfileByUserName(id, requestDTO), HttpStatus.OK);
    }
}
