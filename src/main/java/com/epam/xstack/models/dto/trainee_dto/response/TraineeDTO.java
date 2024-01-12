package com.epam.xstack.models.dto.trainee_dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TraineeDTO {
    private String userName;
    private String firstName;
    private String lastName;
}
