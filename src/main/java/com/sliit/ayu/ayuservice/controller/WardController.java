package com.sliit.ayu.ayuservice.controller;

import com.sliit.ayu.ayuservice.constants.Constants;
import com.sliit.ayu.ayuservice.dto.UserDTO;
import com.sliit.ayu.ayuservice.dto.UserUpdateDTO;
import com.sliit.ayu.ayuservice.dto.WardDTO;
import com.sliit.ayu.ayuservice.dto.WardUpdateDTO;
import com.sliit.ayu.ayuservice.service.UserService;
import com.sliit.ayu.ayuservice.service.WardService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ayu/service/v1")
@Slf4j
public class WardController {
    @Autowired
    private WardService wardService;

    @PostMapping("/ward")
    public ResponseEntity<WardDTO> createWard(@Valid @RequestBody WardDTO wardDTO, @RequestHeader Map<String, String> headers){
        log.info("Ward creation request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        return ResponseEntity.status(HttpStatus.CREATED).body(wardService.addWard(wardDTO));
    }

    @GetMapping("/ward")
    public ResponseEntity<List<WardDTO>> searchWard(@RequestHeader Map<String, String> headers){
        log.info("User search request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));

        return ResponseEntity.status(HttpStatus.OK).body(wardService.searchWard(headers.get("description")));
    }

    @GetMapping("/ward/{wardId}")
    public ResponseEntity<WardDTO> getWard(@PathVariable int wardId, @RequestHeader Map<String, String> headers){
        log.info("User get request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        return ResponseEntity.status(HttpStatus.OK).body(wardService.getWard(wardId));
    }

    @PutMapping("/ward/{wardId}")
    public ResponseEntity<WardDTO> updateWard(@PathVariable int wardId, @RequestBody WardUpdateDTO wardDTO, @RequestHeader Map<String, String> headers){
        log.info("User update request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        wardDTO.setId(wardId);
        return ResponseEntity.status(HttpStatus.CREATED).body(wardService.updateWard(wardDTO));
    }

    @DeleteMapping("/ward/{wardId}")
    public ResponseEntity<Void> deleteWard(@PathVariable int wardId, @RequestHeader Map<String, String> headers){
        log.info("User delete request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        wardService.deleteWard(wardId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
