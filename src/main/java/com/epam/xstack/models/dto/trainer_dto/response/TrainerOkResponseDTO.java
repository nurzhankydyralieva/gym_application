package com.epam.xstack.models.dto.trainer_dto.response;

import com.epam.xstack.models.enums.Code;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainerOkResponseDTO {
    private String response;
    private Code code;
}
