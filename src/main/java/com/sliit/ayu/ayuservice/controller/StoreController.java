package com.sliit.ayu.ayuservice.controller;

import com.sliit.ayu.ayuservice.constants.Constants;
import com.sliit.ayu.ayuservice.dto.StoreDTO;
import com.sliit.ayu.ayuservice.service.StoreService;
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
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping("/store")
    public ResponseEntity<StoreDTO> createStore(@Valid @RequestBody StoreDTO storeDTO, @RequestHeader Map<String, String> headers){
        log.info("Store creation request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        return ResponseEntity.status(HttpStatus.CREATED).body(storeService.addStore(storeDTO));
    }

    @GetMapping("/store")
    public ResponseEntity<List<StoreDTO>> searchStore(@RequestHeader Map<String, String> headers){
        log.info("Store search request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        return ResponseEntity.status(HttpStatus.OK).body(storeService.searchStore(headers.get("name")));
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<StoreDTO> getStore(@PathVariable int storeId, @RequestHeader Map<String, String> headers){
        log.info("Store get request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        return ResponseEntity.status(HttpStatus.OK).body(storeService.getStore(storeId));
    }

    @PutMapping("/store/{storeId}")
    public ResponseEntity<StoreDTO> updateStore(@PathVariable int storeId, @RequestBody StoreDTO storeDTO, @RequestHeader Map<String, String> headers){
        log.info("Store update request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        storeDTO.setId(storeId);
        return ResponseEntity.status(HttpStatus.OK).body(storeService.updateStore(storeDTO));
    }

    @DeleteMapping("/store/{storeId}")
    public ResponseEntity<Void> deleteStore(@PathVariable int storeId, @RequestHeader Map<String, String> headers){
        log.info("Store delete request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        storeService.deleteStore(storeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
