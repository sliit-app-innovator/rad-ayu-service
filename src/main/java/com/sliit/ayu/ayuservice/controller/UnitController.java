package com.sliit.ayu.ayuservice.controller;

import com.sliit.ayu.ayuservice.constants.Constants;
import com.sliit.ayu.ayuservice.dto.StoreDTO;
import com.sliit.ayu.ayuservice.dto.UnitAddDTO;
import com.sliit.ayu.ayuservice.dto.UnitDTO;
import com.sliit.ayu.ayuservice.service.UnitService;

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
public class UnitController {

    @Autowired
    private UnitService unitService; 

    @GetMapping("/unit")
    public ResponseEntity<List<UnitDTO>> searchUnit(@RequestHeader Map<String, String> headers){
        log.info("Medicine Category search request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        return ResponseEntity.status(HttpStatus.OK).body(unitService.searchUnit(headers.get("unit")));
    }
    

    @PostMapping("/unit")
    public ResponseEntity<UnitDTO> addUnit(@Valid @RequestBody UnitDTO unitAddDTO, @RequestHeader Map<String, String> headers){
        log.info("Unit creation request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        return ResponseEntity.status(HttpStatus.CREATED).body(unitService.addUnit(unitAddDTO));
    }  
    
    @PutMapping("/unit/{id}")
    public ResponseEntity<UnitDTO> updateUnit(@PathVariable int id, @RequestBody UnitDTO unitDTO, @RequestHeader Map<String, String> headers){
        log.info("Unit update request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        unitDTO.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(unitService.updateUnit(unitDTO));
    }

    @GetMapping("/unit/{id}")
    public ResponseEntity<UnitDTO> getUnit(@PathVariable int id, @RequestHeader Map<String, String> headers){
        log.info("Unit get request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        return ResponseEntity.status(HttpStatus.OK).body(unitService.getUnit(id));
    }

    @DeleteMapping("/unit/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable int id, @RequestHeader Map<String, String> headers){
        log.info("unit delete request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        unitService.deleteUnit(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
