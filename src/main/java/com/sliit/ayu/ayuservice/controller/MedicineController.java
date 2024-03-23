package com.sliit.ayu.ayuservice.controller;

import com.sliit.ayu.ayuservice.constants.Constants;
import com.sliit.ayu.ayuservice.dto.MedicineDTO;
import com.sliit.ayu.ayuservice.dto.MedicineTypeDTO;
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
    private MedicineService medicineService;

    @PostMapping("/medicine")
    public ResponseEntity<MedicineDTO> addMedicine(@Valid @RequestBody MedicineDTO medicineDTO, @RequestHeader Map<String, String> headers){
        log.info("User creation request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        return ResponseEntity.status(HttpStatus.CREATED).body(medicineService.addMedicine(medicineDTO));
    }

    @GetMapping("/medicine")
    public ResponseEntity<List<MedicineDTO>> searchMedicine(@RequestHeader Map<String, String> headers){
        log.info("User search request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        return ResponseEntity.status(HttpStatus.OK).body(medicineService.searchMedicine(headers.get("name")));
    }

    @GetMapping("/medicine/{medicineId}")
    public ResponseEntity<MedicineDTO> getMedicine(@PathVariable int medicineId, @RequestHeader Map<String, String> headers){
        log.info("User get request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        return ResponseEntity.status(HttpStatus.OK).body(medicineService.getMedicine(medicineId));
    }

    @PutMapping("/medicine/{medicineId}")
    public ResponseEntity<MedicineDTO> updateMedicine(@PathVariable int medicineId, @RequestBody MedicineUpdateDTO medicineDTO, @RequestHeader Map<String, String> headers){
        log.info("User update request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        medicineDTO.setId(medicineId);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicineService.updateMedicine(medicineDTO));
    }

    @DeleteMapping("/medicine/{medicineId}")
    public ResponseEntity<Void> deleteMedicine(@PathVariable int medicineId, @RequestHeader Map<String, String> headers){
        log.info("User delete request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        medicineService.deleteMedicine(medicineId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/medicine/type")
    public ResponseEntity<MedicineTypeDTO> addMedicineType(@Valid @RequestBody MedicineTypeDTO medicineTypeDTO, @RequestHeader Map<String, String> headers){
        log.info("User creation request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        return ResponseEntity.status(HttpStatus.CREATED).body(medicineService.addMedicineType(medicineTypeDTO));
    }

    @GetMapping("/medicine/type")
    public ResponseEntity<List<MedicineTypeDTO>> searchMedicineType(@RequestHeader Map<String, String> headers){
        log.info("User search request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        return ResponseEntity.status(HttpStatus.OK).body(medicineService.searchMedicineType(headers.get("name")));
    }

    @GetMapping("/medicine/type/{typeId}")
    public ResponseEntity<MedicineTypeDTO> getMedicineType(@PathVariable int typeId, @RequestHeader Map<String, String> headers){
        log.info("User get request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        return ResponseEntity.status(HttpStatus.OK).body(medicineService.getMedicineType(typeId));
    }

    @PutMapping("/medicine/type/{typeId}")
    public ResponseEntity<MedicineTypeDTO> updateMedicineType(@PathVariable int typeId, @RequestBody MedicineTypeDTO medicineTypeDTO, @RequestHeader Map<String, String> headers){
        log.info("User update request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        medicineTypeDTO.setId(typeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicineService.updateMedicineType(medicineTypeDTO));
    }

    @DeleteMapping("/medicine/type/{typeId}")
    public ResponseEntity<Void> deleteMedicineType(@PathVariable int typeId, @RequestHeader Map<String, String> headers){
        log.info("User delete request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        medicineService.deleteMedicineType(typeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
