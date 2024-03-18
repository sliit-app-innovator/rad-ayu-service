package com.sliit.ayu.ayuservice.controller;

import com.sliit.ayu.ayuservice.constants.Constants;
import com.sliit.ayu.ayuservice.dto.StoreTypeDTO;
import com.sliit.ayu.ayuservice.dto.UserDTO;
import com.sliit.ayu.ayuservice.service.StoreTypeService;
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
public class StoreTypeController {

    @Autowired
    private StoreTypeService storeTypeService;

    @GetMapping("/store-type")
    public ResponseEntity<List<StoreTypeDTO>> searchStoreType(@RequestHeader Map<String, String> headers){
        log.info("Store Type search request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        return ResponseEntity.status(HttpStatus.OK).body(storeTypeService.searchStoreType(headers.get("name")));
    }
}
