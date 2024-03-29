package com.epam.xstack.dao.authentication_dao.impl;

import com.epam.xstack.aspects.authentication_aspects.annotations.AuthenticationChangeLoginAspectAnnotation;
import com.epam.xstack.aspects.authentication_aspects.annotations.AuthenticationLoginAspectAnnotation;
import com.epam.xstack.dao.authentication_dao.AuthenticationDAO;
import com.epam.xstack.exceptions.dao_exceptions.UserNameOrPasswordNotCorrectException;
import com.epam.xstack.mapper.authentication_mapper.AuthenticationChangeLoginRequestMapper;
import com.epam.xstack.models.dto.authentication_dto.AuthenticationChangeLoginRequestDTO;
import com.epam.xstack.models.dto.authentication_dto.AuthenticationRequestDTO;
import com.epam.xstack.models.dto.authentication_dto.AuthenticationResponseDTO;
import com.epam.xstack.models.entity.User;
import com.epam.xstack.models.enums.Code;
import com.epam.xstack.validation.AuthValidation;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor

public class AuthenticationDAOImpl implements AuthenticationDAO {
    private final SessionFactory sessionFactory;
    private final AuthenticationChangeLoginRequestMapper requestMapper;
    private final AuthValidation validation;

    @Override
    @Transactional
    @AuthenticationLoginAspectAnnotation
    public AuthenticationResponseDTO authenticateLogin(UUID id, AuthenticationRequestDTO requestDTO) {

        validation.authValidator(id, requestDTO);

        return AuthenticationResponseDTO
                .builder()
                .response("Login response")
                .code(Code.STATUS_200_OK)
                .build();
    }

    @Override
    @Transactional
    @AuthenticationChangeLoginAspectAnnotation
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
            throw UserNameOrPasswordNotCorrectException
                    .builder()
                    .codeStatus(Code.USER_NOT_FOUND)
                    .message("User not exists in database")
                    .httpStatus(HttpStatus.UNAUTHORIZED)
                    .build();
        }
    }
}
