package com.epam.xstack.models.dto.authentication_dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequestDTO {
     @NotEmpty(message = "User name should not be empty")
     String userName;
     @NotEmpty(message = "Password should not be empty")
     String password;
}
