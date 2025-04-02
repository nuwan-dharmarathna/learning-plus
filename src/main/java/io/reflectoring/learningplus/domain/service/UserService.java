package io.reflectoring.learningplus.domain.service;

import io.reflectoring.learningplus.domain.dto.UserDTO;
import io.reflectoring.learningplus.domain.models.Users;
import io.reflectoring.learningplus.domain.repository.UserRepository;
import io.reflectoring.learningplus.domain.request.UserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private static final String UserService = "UserService";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
    public ResponseEntity<UserDTO> register(UserRequest userRequest) {
        logger.info("INITIAL,session_id,{},func_get_args(),REQUEST RECEIVED,{},{}, {}, {},args.get(0)", UserService, null, false, true, userRequest);

        if (userRequest.getFirstName() == null || userRequest.getLastName() == null || userRequest.getUsername() == null || userRequest.getPassword() == null || userRequest.getEmail() == null) {
            sendBadResponse("Please fill in all required fields");
        }

        if (userRepository.findByUsername(userRequest.getUsername()) != null) {
            sendBadResponse("Username already exists");
        }

        if (userRepository.findByEmail(userRequest.getEmail()) != null) {
            sendBadResponse("Email already exists");
        }

        if (!Objects.equals(userRequest.getPassword(), userRequest.getPasswordConfirm())) {
            sendBadResponse("Passwords do not match");
        }

        Users userDetails = new Users();

        userDetails.setFirstName(userRequest.getFirstName());
        userDetails.setLastName(userRequest.getLastName());
        userDetails.setEmail(userRequest.getEmail());
        userDetails.setUsername(userRequest.getUsername());
        userDetails.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        userDetails.setActive(true);

        userRepository.save(userDetails);

        UserDTO user = new UserDTO();

        user.setStatus("200");
        user.setRequestStatus("success");
        user.setMessage("User registered successfully");
        user.setUsers(userDetails);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity<UserDTO> login(UserRequest userRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));

        if (!authentication.isAuthenticated()) {
            sendBadResponse("Invalid username or password");
        }

        Users user = new Users();

        user = userRepository.findByUsername(user.getUsername());
        String token =  jwtService.generateToken(user.getUsername());

        UserDTO userDTO = new UserDTO();
        userDTO.setStatus("200");
        userDTO.setRequestStatus("success");
        userDTO.setMessage("User logged in successfully");
        userDTO.setToken(token);
        userDTO.setUsers(user);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    //    implement send-bad-response
    public void sendBadResponse(String message) {
        UserDTO user = new UserDTO();
        user.setStatus("400");
        user.setRequestStatus("failed");
        user.setMessage(message);
        new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
    }

}
