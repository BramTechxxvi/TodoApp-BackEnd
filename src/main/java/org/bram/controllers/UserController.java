package org.bram.controllers;

import jakarta.validation.Valid;
import org.bram.dtos.requests.*;
import org.bram.dtos.response.*;
import org.bram.exceptions.*;
import org.bram.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody @Valid RegisterUserRequest request) {
        try {
            RegisterUserResponse response = userServices.registerUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch(DetailsAlreadyInUseException e) {
            RegisterUserResponse response = new RegisterUserResponse();
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        try {
            LoginResponse response = userServices.login(request);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch(InvalidCredentialsException e) {
            LoginResponse response = userServices.login(request);
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @PutMapping("/changePassword/{id}")
    public ResponseEntity<ChangePasswordResponse> changePassword(@PathVariable("id") String id, @RequestBody ChangePasswordRequest request) {
        try {
            request.setUserId(id);
            ChangePasswordResponse response = userServices.changePassword(request);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (UserNotFoundException e) {
            ChangePasswordResponse response = new ChangePasswordResponse();
            response.setMessage("User not found");
            response.setSuccess(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (UserNotLoggedInException e) {
            ChangePasswordResponse response = new ChangePasswordResponse();
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);

        }catch (SamePasswordException | IncorrectOldPasswordException e) {
            ChangePasswordResponse errorResponse = new ChangePasswordResponse();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PutMapping("/changeEmail/{id}")
    public ResponseEntity<ChangeEmailResponse> changeEmail(@PathVariable("id") String id, @RequestBody ChangeEmailRequest request) {
        try {
            request.setUserId(id);
            ChangeEmailResponse response = userServices.changeEmail(request);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch(UserNotFoundException e) {
            ChangeEmailResponse errorResponse = new ChangeEmailResponse();
            errorResponse.setMessage(e.getMessage());
            errorResponse.setSuccess(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

        } catch (UserNotLoggedInException e) {
            ChangeEmailResponse response = new ChangeEmailResponse();
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);

        }catch (SameEmailException | IncorrectOldPasswordException e) {
            ChangeEmailResponse errorResponse = new ChangeEmailResponse();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PostMapping("/logout/{id}")
    public ResponseEntity<UserLogoutResponse> logout(@PathVariable("id") String id, @RequestBody UserLogoutRequest request) {
        try {
            request.setUserId(id);
            UserLogoutResponse response = userServices.logout(request);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (UserNotFoundException e) {
            UserLogoutResponse response = new UserLogoutResponse();
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (UserNotLoggedInException e) {
            UserLogoutResponse response = new UserLogoutResponse();
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
