package com.epam.xstack.models.dto.training_dto.response;

import com.epam.xstack.models.enums.Code;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingSaveResponseDTO {
    private String response;
    private Code code;
}
