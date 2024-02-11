package com.epam.xstack.controller;

import com.epam.xstack.models.dto.training_type_dto.TrainingTypeDTO;
import com.epam.xstack.service.training_type_service.TrainingTypeService;
import com.epam.xstack.validation.NotNullValidation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/training_types")
@Api(tags = "Training type controller")
public class TrainingTypeController {
    private final TrainingTypeService trainingTypeService;
    private final NotNullValidation validation;

    @ApiOperation(value = "Save Training Type to database")
    @PostMapping("/save")
    public ResponseEntity<TrainingTypeDTO> save(@Valid @RequestBody TrainingTypeDTO trainingTypeDTO, BindingResult result) {
        validation.nullValidation(result);
        return new ResponseEntity<>(trainingTypeService.save(trainingTypeDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Get all Trainings")
    @GetMapping("/all")
    public ResponseEntity<List<TrainingTypeDTO>> findAll() {
        return new ResponseEntity<>(trainingTypeService.findAll(), HttpStatus.FOUND);
    }

}
