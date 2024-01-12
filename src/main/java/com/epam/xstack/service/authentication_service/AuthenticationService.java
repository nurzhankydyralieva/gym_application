package com.epam.xstack.service.authentication_service;

import com.epam.xstack.models.dto.authentication_dto.AuthenticationChangeLoginRequestDTO;
import com.epam.xstack.models.dto.authentication_dto.AuthenticationRequestDTO;
import com.epam.xstack.models.dto.authentication_dto.AuthenticationResponseDTO;

import java.util.UUID;


public interface AuthenticationService {

    AuthenticationResponseDTO authenticateLogin(UUID id, AuthenticationRequestDTO requestDTO);
    AuthenticationResponseDTO authenticationChangeLogin(UUID id, AuthenticationChangeLoginRequestDTO requestDTO);

}
