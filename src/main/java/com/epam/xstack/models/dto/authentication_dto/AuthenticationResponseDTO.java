package com.epam.xstack.models.dto.authentication_dto;

import com.epam.xstack.models.enums.Code;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponseDTO {
    private String response;
    private Code code;
}
