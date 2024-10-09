package com.aksoyakin.pawtientcarebe.controller;

import com.aksoyakin.pawtientcarebe.dto.UserDto;
import com.aksoyakin.pawtientcarebe.dto.converter.EntityConverter;
import com.aksoyakin.pawtientcarebe.dto.request.RegistrationRequest;
import com.aksoyakin.pawtientcarebe.dto.response.ApiResponse;
import com.aksoyakin.pawtientcarebe.exception.UserAlreadyExistsException;
import com.aksoyakin.pawtientcarebe.model.User;
import com.aksoyakin.pawtientcarebe.service.user.UserService;
import com.aksoyakin.pawtientcarebe.utils.FeedBackMessage;
import com.aksoyakin.pawtientcarebe.utils.UrlMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping(UrlMapping.USERS)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final EntityConverter<User, UserDto> entityConverter;

    @PostMapping
    public ResponseEntity<ApiResponse> register(@RequestBody RegistrationRequest registrationRequest) {
        try {
            User user = userService.add(registrationRequest);
            UserDto registeredUser = entityConverter.mapEntityToDto(user, UserDto.class);
            return ResponseEntity
                    .ok(new ApiResponse(FeedBackMessage.SUCCESS, registeredUser));
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity
                    .status(CONFLICT)
                    .body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity
                    .status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }
}
