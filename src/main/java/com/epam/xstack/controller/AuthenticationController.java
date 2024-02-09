package com.epam.xstack.controller;

import com.epam.xstack.exceptions.AccessDeniedException;
import com.epam.xstack.models.dto.authentication_dto.AuthenticationChangeLoginRequestDTO;
import com.epam.xstack.models.dto.authentication_dto.AuthenticationRequestDTO;
import com.epam.xstack.models.dto.authentication_dto.AuthenticationResponseDTO;
import com.epam.xstack.service.authentication_service.AuthenticationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Api(tags = "Authentication controller")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User authenticated successfully"),
            @ApiResponse(code = 403, message = "Access denied, check user name or id"),
            @ApiResponse(code = 401, message = "Bad credentials"),
            @ApiResponse(code = 422, message = "User or password is null"),
            @ApiResponse(code = 404, message = "User with user name or id not found")
    })
    @ApiOperation(value = "Login the user")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AccessDeniedException.class)
    @GetMapping("/{id}")
    public ResponseEntity<AuthenticationResponseDTO> login(@PathVariable("id") UUID id, @Valid @RequestBody AuthenticationRequestDTO requestDTO) {

        try {
            AuthenticationResponseDTO responseDTO = authenticationService.authenticateLogin(id, requestDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (AccessDeniedException e) {
            throw new AccessDeniedException("Access denied, check user name or password");
        }
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User is updated"),
            @ApiResponse(code = 403, message = "Access denied, check user name or id"),
            @ApiResponse(code = 401, message = "Bad credentials"),
            @ApiResponse(code = 422, message = "User or password is null"),
            @ApiResponse(code = 404, message = "User with user name or id not found")
    })
    @ApiOperation(value = "Changes login")
    @PutMapping("/update/{id}")
    public ResponseEntity<AuthenticationResponseDTO> updateLogin(@PathVariable("id") UUID id, @Valid @RequestBody AuthenticationChangeLoginRequestDTO requestDTO) {
        return new ResponseEntity<>(authenticationService.authenticationChangeLogin(id, requestDTO), HttpStatus.OK);
    }

}
