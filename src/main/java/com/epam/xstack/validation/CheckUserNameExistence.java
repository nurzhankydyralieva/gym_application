package com.epam.xstack.validation;

import com.epam.xstack.exceptions.UserAlreadyExistsException;
import com.epam.xstack.models.entity.User;
import com.epam.xstack.models.enums.Code;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CheckUserNameExistence {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckUserNameExistence.class);
    private final SessionFactory sessionFactory;

    public void userNameExists(String userName) {

        Session session = sessionFactory.openSession();
        User result = session.createQuery("FROM User u WHERE u.userName=:userName", User.class)
                .setParameter("userName", userName).uniqueResult();

        if (result != null) {
            LOGGER.info("The user name already exists in database");
            throw UserAlreadyExistsException.builder()
                    .codeStatus(Code.NULL_NOT_ALLOWED).message("The user name already exists in database")
                    .httpStatus(HttpStatus.BAD_REQUEST).build();
        }
    }
}
