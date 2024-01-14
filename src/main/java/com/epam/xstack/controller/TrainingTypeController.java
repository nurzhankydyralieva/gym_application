package com.epam.xstack.controller;

import com.epam.xstack.models.dto.training_type_dto.TrainingTypeDTO;
import com.epam.xstack.service.training_type_service.TrainingTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/training_types")
@Api(tags = "Training type controller")
public class TrainingTypeController {
    private final TrainingTypeService trainingTypeService;

    @ApiOperation(value = "Save Training Type to database")
    @PostMapping("/save")
    public ResponseEntity<TrainingTypeDTO> save(@RequestBody TrainingTypeDTO trainingTypeDTO) {
        return new ResponseEntity<>(trainingTypeService.save(trainingTypeDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Select all Trainings")
    @GetMapping("/all")
    public ResponseEntity<List<TrainingTypeDTO>> findAll() {
        return new ResponseEntity<>(trainingTypeService.findAll(), HttpStatus.FOUND);
    }

}
