package com.sliit.ayu.ayuservice.controller;

import com.sliit.ayu.ayuservice.constants.Constants;
import com.sliit.ayu.ayuservice.dto.MedicineDTO;
import com.sliit.ayu.ayuservice.dto.MedicineUpdateDTO;
import com.sliit.ayu.ayuservice.service.MedicineService;
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
public class MedicineController {

    @Autowired
    private MedicineService userService;

    @PostMapping("/medicine")
    public ResponseEntity<MedicineDTO> addMedicine(@Valid @RequestBody MedicineDTO medicineDTO, @RequestHeader Map<String, String> headers){
        log.info("User creation request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addMedicine(medicineDTO));
    }

    @GetMapping("/medicine")
    public ResponseEntity<List<MedicineDTO>> searchMedicine(@RequestHeader Map<String, String> headers){
        log.info("User search request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        return ResponseEntity.status(HttpStatus.OK).body(userService.searchMedicine(headers.get("name")));
    }

    @GetMapping("/medicine/{medicineId}")
    public ResponseEntity<MedicineDTO> getMedicine(@PathVariable int medicineId, @RequestHeader Map<String, String> headers){
        log.info("User get request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        return ResponseEntity.status(HttpStatus.OK).body(userService.getMedicine(medicineId));
    }

    @PutMapping("/medicine/{medicineId}")
    public ResponseEntity<MedicineDTO> updateMedicine(@PathVariable int medicineId, @RequestBody MedicineUpdateDTO medicineDTO, @RequestHeader Map<String, String> headers){
        log.info("User update request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        medicineDTO.setId(medicineId);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateMedicine(medicineDTO));
    }

    @DeleteMapping("/medicine/{medicineId}")
    public ResponseEntity<Void> deleteMedicine(@PathVariable int medicineId, @RequestHeader Map<String, String> headers){
        log.info("User delete request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        userService.deleteMedicine(medicineId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
