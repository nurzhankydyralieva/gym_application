package com.epam.xstack.controller;

import com.epam.xstack.models.dto.trainer_dto.request.TrainerActivateDeActivateDTO;
import com.epam.xstack.models.dto.trainer_dto.request.TrainerProfileSelectRequestDTO;
import com.epam.xstack.models.dto.trainer_dto.request.TrainerProfileUpdateRequestDTO;
import com.epam.xstack.models.dto.trainer_dto.request.TrainerRegistrationRequestDTO;
import com.epam.xstack.models.dto.trainer_dto.response.TrainerOkResponseDTO;
import com.epam.xstack.models.dto.trainer_dto.response.TrainerProfileSelectResponseDTO;
import com.epam.xstack.models.dto.trainer_dto.response.TrainerProfileUpdateResponseDTO;
import com.epam.xstack.models.dto.trainer_dto.response.TrainerRegistrationResponseDTO;
import com.epam.xstack.service.trainer_service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/trainers")
@RequiredArgsConstructor
public class TrainerController {
    private final TrainerService trainerService;

    @PostMapping("/save")
    public ResponseEntity<TrainerRegistrationResponseDTO> saveTrainee(@RequestBody TrainerRegistrationRequestDTO requestDTO) {
        return new ResponseEntity<>(trainerService.saveTrainer(requestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainerProfileSelectResponseDTO> selectTrainerProfile(@PathVariable("id") UUID id, @RequestBody TrainerProfileSelectRequestDTO requestDTO) {
        return new ResponseEntity<>(trainerService.selectTrainerProfileByUserName(id, requestDTO), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TrainerProfileUpdateResponseDTO> updateUser(@PathVariable("id") UUID id, @RequestBody TrainerProfileUpdateRequestDTO requestDTO) {
        return new ResponseEntity<>(trainerService.updateTrainerProfile(id, requestDTO), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TrainerOkResponseDTO> updateActivateDe_ActivateTrainer(@PathVariable("id") UUID id, @RequestBody TrainerActivateDeActivateDTO dto) {
        return new ResponseEntity<>(trainerService.activateDe_ActivateTrainer(id, dto), HttpStatus.OK);
    }
}
