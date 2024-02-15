package com.epam.xstack.validation;

import com.epam.xstack.exceptions.dao_exceptions.UserNameOrPasswordNotCorrectException;
import com.epam.xstack.mapper.authentication_mapper.AuthenticationRequestMapper;
import com.epam.xstack.models.dto.authentication_dto.AuthenticationRequestDTO;
import com.epam.xstack.models.entity.User;
import com.epam.xstack.models.enums.Code;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthValidation {
    private final SessionFactory sessionFactory;
    private final AuthenticationRequestMapper authenticationRequestMapper;

    public void authValidator(UUID id, AuthenticationRequestDTO requestDTO) {
        Session session = sessionFactory.getCurrentSession();
        User userId = session.get(User.class, id);
        User user = authenticationRequestMapper.toEntity(requestDTO);
        if (!userId.getUserName().equals(user.getUserName()) || !userId.getPassword().equals(user.getPassword())) {
            throw UserNameOrPasswordNotCorrectException.builder()
                    .codeStatus(Code.USER_NOT_FOUND)
                    .message("User name: " + user.getUserName() + " or password: " + user.getPassword() + " not correct")
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .build();
        }
        authenticationRequestMapper.toDto(user);
    }
}
