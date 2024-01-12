package com.epam.xstack.models.dto.trainee_dto.response;

import com.epam.xstack.models.dto.trainer_dto.response.TrainerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TraineeProfileResponseDTO {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String address;
    private Boolean isActive;
    private Collection<TrainerDTO> trainers;
}