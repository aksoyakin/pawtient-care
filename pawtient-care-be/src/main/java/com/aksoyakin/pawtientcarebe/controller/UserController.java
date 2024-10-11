package com.aksoyakin.pawtientcarebe.controller;

import com.aksoyakin.pawtientcarebe.dto.UserDto;
import com.aksoyakin.pawtientcarebe.dto.converter.EntityConverter;
import com.aksoyakin.pawtientcarebe.dto.request.RegistrationRequest;
import com.aksoyakin.pawtientcarebe.dto.request.UserUpdateRequest;
import com.aksoyakin.pawtientcarebe.dto.response.ApiResponse;
import com.aksoyakin.pawtientcarebe.exception.ResourceNotFoundException;
import com.aksoyakin.pawtientcarebe.exception.UserAlreadyExistsException;
import com.aksoyakin.pawtientcarebe.model.User;
import com.aksoyakin.pawtientcarebe.service.user.impl.UserServiceImpl;
import com.aksoyakin.pawtientcarebe.utils.FeedBackMessage;
import com.aksoyakin.pawtientcarebe.utils.UrlMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(UrlMapping.USERS)
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final EntityConverter<User, UserDto> entityConverter;

    @PostMapping(UrlMapping.REGISTER_USER)
    public ResponseEntity<ApiResponse> register(@RequestBody RegistrationRequest registrationRequest) {
        try {
            User user = userServiceImpl.register(registrationRequest);
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

    @PutMapping(UrlMapping.UPDATE_USER)
    public ResponseEntity<ApiResponse> update(@PathVariable Long userId, @RequestBody UserUpdateRequest userUpdateRequest) {
        try {
            User user = userServiceImpl.update(userId, userUpdateRequest);
            UserDto updatedUser = entityConverter.mapEntityToDto(user, UserDto.class);
            return ResponseEntity
                    .ok(new ApiResponse(FeedBackMessage.UPDATE_SUCCESS, updatedUser));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity
                    .status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity
                    .status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }
}
