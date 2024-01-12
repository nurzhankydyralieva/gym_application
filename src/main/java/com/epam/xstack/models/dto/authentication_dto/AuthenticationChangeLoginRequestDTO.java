package com.epam.xstack.models.dto.authentication_dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationChangeLoginRequestDTO {
    private String userName;
    private String oldPassword;
    private String newPassword;
}
