package io.reflectoring.learningplus.application.controller;

import io.reflectoring.learningplus.domain.dto.UserDTO;
import io.reflectoring.learningplus.domain.request.UserRequest;
import io.reflectoring.learningplus.domain.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private static final String UserController = "UserController";

    @PostMapping("/sign-up")
    public ResponseEntity<UserDTO> register(@RequestBody(required = true) UserRequest userRequest) {
        logger.info("INITIAL,session_id,{},func_get_args(),REQUEST RECEIVED,{},{}, {}, {},args.get(0)", UserController, null, false, true, userRequest);
        return userService.register(userRequest);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<UserDTO> login(@RequestBody UserRequest userRequest) {
        logger.info("INITIAL,session_id,{},func_get_args(),REQUEST RECEIVED,{},{}, {}, {},args.get(0)", UserController, null, false, true, userRequest);
        return userService.login(userRequest);
    }
}
