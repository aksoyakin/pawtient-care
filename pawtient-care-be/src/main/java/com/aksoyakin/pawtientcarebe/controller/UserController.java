package com.aksoyakin.pawtientcarebe.controller;

import com.aksoyakin.pawtientcarebe.dto.UserDto;
import com.aksoyakin.pawtientcarebe.dto.converter.EntityConverter;
import com.aksoyakin.pawtientcarebe.dto.request.RegistrationRequest;
import com.aksoyakin.pawtientcarebe.dto.request.UserUpdateRequest;
import com.aksoyakin.pawtientcarebe.dto.response.ApiResponse;
import com.aksoyakin.pawtientcarebe.exception.AlreadyExists;
import com.aksoyakin.pawtientcarebe.exception.ResourceNotFoundException;
import com.aksoyakin.pawtientcarebe.model.User;
import com.aksoyakin.pawtientcarebe.service.user.UserService;
import com.aksoyakin.pawtientcarebe.utils.FeedBackMessage;
import com.aksoyakin.pawtientcarebe.utils.UrlMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(UrlMapping.USERS)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final EntityConverter<User, UserDto> entityConverter;

    @PostMapping(UrlMapping.REGISTER_USER)
    public ResponseEntity<ApiResponse> register(@RequestBody RegistrationRequest registrationRequest) {
        try {
            User user = userService.register(registrationRequest);
            UserDto registeredUser = entityConverter.mapEntityToDto(user, UserDto.class);
            return ResponseEntity
                    .ok(new ApiResponse(FeedBackMessage.CREATE_SUCCESS, registeredUser));
        } catch (AlreadyExists e) {
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
            User user = userService.update(userId, userUpdateRequest);
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

    @GetMapping(UrlMapping.GET_USER_BY_ID)
    public ResponseEntity<ApiResponse> findById(@PathVariable Long userId) {
        try {
            UserDto userDto = userService.getUserWithDetails(userId);
            return ResponseEntity
                    .status(FOUND)
                    .body(new ApiResponse(FeedBackMessage.RESOURCE_FOUND, userDto));
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

    @DeleteMapping(UrlMapping.DELETE_USER_BY_ID)
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long userId) {
        try {
            userService.delete(userId);
            return ResponseEntity
                    .ok(new ApiResponse(FeedBackMessage.DELETE_SUCCESS, null));
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

    @GetMapping(UrlMapping.GET_ALL_USERS)
    public ResponseEntity<ApiResponse> getAllUsers() {
        List<UserDto> usersDto = userService.getAllUsers();
        return ResponseEntity
                .status(FOUND)
                .body(new ApiResponse(FeedBackMessage.RESOURCE_FOUND, usersDto));
    }
}
