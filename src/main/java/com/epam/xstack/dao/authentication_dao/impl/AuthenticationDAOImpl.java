package com.epam.xstack.dao.authentication_dao.impl;

import com.epam.xstack.dao.authentication_dao.AuthenticationDAO;
import com.epam.xstack.mapper.authentication_mapper.AuthenticationChangeLoginRequestMapper;
import com.epam.xstack.mapper.authentication_mapper.AuthenticationRequestMapper;
import com.epam.xstack.models.dto.authentication_dto.AuthenticationChangeLoginRequestDTO;
import com.epam.xstack.models.dto.authentication_dto.AuthenticationRequestDTO;
import com.epam.xstack.models.dto.authentication_dto.AuthenticationResponseDTO;
import com.epam.xstack.models.entity.User;
import com.epam.xstack.models.enums.Code;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor

public class AuthenticationDAOImpl implements AuthenticationDAO {
    private final SessionFactory sessionFactory;
    private final AuthenticationRequestMapper authenticationRequestMapper;
    private final AuthenticationChangeLoginRequestMapper requestMapper;

    @Override
    @Transactional
    public AuthenticationResponseDTO authenticateLogin(UUID id, AuthenticationRequestDTO requestDTO) {
        Session session = sessionFactory.getCurrentSession();
        User user = authenticationRequestMapper.toEntity(requestDTO);
        User userId = session.get(User.class, id);

        if (userId.getUserName().equals(user.getUserName()) && userId.getPassword().equals(user.getPassword())) {
            authenticationRequestMapper.toDto(user);
            return AuthenticationResponseDTO
                    .builder()
                    .response("Login response")
                    .code(Code.STATUS_200_OK)
                    .build();
        } else {
            throw new RuntimeException("Not available");
        }
    }

    @Override
    @Transactional
    public AuthenticationResponseDTO authenticationChangeLogin(UUID id, AuthenticationChangeLoginRequestDTO requestDTO) {
        Session session = sessionFactory.getCurrentSession();
        User userToBeUpdated = session.get(User.class, id);
        User user = requestMapper.toEntity(requestDTO);


        if (userToBeUpdated.getPassword().equals(user.getPassword())) {
            userToBeUpdated.setUserName(requestDTO.getUserName());
            userToBeUpdated.setPassword(requestDTO.getNewPassword());
            session.update(userToBeUpdated);
            return AuthenticationResponseDTO
                    .builder()
                    .response("Login change response")
                    .code(Code.STATUS_200_OK)
                    .build();
        } else {
            throw new RuntimeException("not available");
        }
    }
}
