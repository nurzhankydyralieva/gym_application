package com.epam.xstack.dao.authentication_dao.impl;

import com.epam.xstack.mapper.authentication_mapper.AuthenticationChangeLoginRequestMapper;
import com.epam.xstack.models.dto.authentication_dto.AuthenticationChangeLoginRequestDTO;
import com.epam.xstack.models.dto.authentication_dto.AuthenticationRequestDTO;
import com.epam.xstack.models.dto.authentication_dto.AuthenticationResponseDTO;
import com.epam.xstack.models.entity.User;
import com.epam.xstack.models.enums.Code;
import com.epam.xstack.validation.AuthValidation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationDAOImplTest {
    @InjectMocks
    private AuthenticationDAOImpl service;
    @Mock
    private AuthValidation validation;
    @Mock
    private SessionFactory sessionFactory;
    @Mock
    private Session session;
    @Mock
    private AuthenticationChangeLoginRequestMapper requestMapper;

    @Test
    public void testShouldAuthenticateLogin() {
        UUID id = UUID.randomUUID();
        AuthenticationRequestDTO requestDTO = new AuthenticationRequestDTO("username", "password");
        doNothing().when(validation).authValidator(id, requestDTO);

        AuthenticationResponseDTO responseDTO = service.authenticateLogin(id, requestDTO);

        assertEquals("Login response", responseDTO.getResponse());
        assertEquals(Code.STATUS_200_OK, responseDTO.getCode());
    }

    @Test
    public void testShouldChangeLoginAuthentication() {
        UUID uuid = UUID.randomUUID();
        String userName = "Scarlett.Johansson";
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";
        AuthenticationChangeLoginRequestDTO requestDTO = new AuthenticationChangeLoginRequestDTO();
        requestDTO.setUserName(userName);
        requestDTO.setNewPassword(newPassword);
        requestDTO.setOldPassword(oldPassword);
        User userToBeUpdated = new User();
        userToBeUpdated.setId(uuid);
        userToBeUpdated.setUserName(userName);
        userToBeUpdated.setPassword(newPassword);

        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.get(User.class, uuid)).thenReturn(userToBeUpdated);
        when(requestMapper.toEntity(requestDTO)).thenReturn(userToBeUpdated);

        AuthenticationResponseDTO responseDTO = service.authenticationChangeLogin(uuid, requestDTO);

        verify(session).update(userToBeUpdated);
        assertNotNull(responseDTO);
    }
}
