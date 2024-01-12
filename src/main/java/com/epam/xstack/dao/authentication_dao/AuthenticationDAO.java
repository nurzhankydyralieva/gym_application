package com.epam.xstack.dao.authentication_dao;

import com.epam.xstack.models.dto.authentication_dto.AuthenticationChangeLoginRequestDTO;
import com.epam.xstack.models.dto.authentication_dto.AuthenticationRequestDTO;
import com.epam.xstack.models.dto.authentication_dto.AuthenticationResponseDTO;

import java.util.UUID;

public interface AuthenticationDAO {
    AuthenticationResponseDTO authenticateLogin(UUID id, AuthenticationRequestDTO requestDTO);
    AuthenticationResponseDTO authenticationChangeLogin(UUID id, AuthenticationChangeLoginRequestDTO requestDTO);
}
