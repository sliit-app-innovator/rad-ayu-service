package com.sliit.ayu.ayuservice.controller;

import com.sliit.ayu.ayuservice.constants.Constants;
import com.sliit.ayu.ayuservice.dto.UserDTO;
import com.sliit.ayu.ayuservice.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/ayu/service/v1/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO, @RequestHeader Map<String, String> headers){
        log.info("User creation request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(userDTO));
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> searchUser(@RequestHeader Map<String, String> headers){
        log.info("User search request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        return ResponseEntity.status(HttpStatus.OK).body(userService.searchUser(headers.get("name")));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable int id, @RequestHeader Map<String, String> headers){
        log.info("User get request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(id));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @RequestBody UserDTO userDTO, @RequestHeader Map<String, String> headers){
        log.info("User update request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        userDTO.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateUser(userDTO));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id, @RequestHeader Map<String, String> headers){
        log.info("User delete request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
