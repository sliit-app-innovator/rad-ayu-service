package com.sliit.ayu.ayuservice.controller;

import com.sliit.ayu.ayuservice.constants.Constants;
import com.sliit.ayu.ayuservice.dto.MedicineCategoryDTO;
import com.sliit.ayu.ayuservice.dto.UnitDTO;
import com.sliit.ayu.ayuservice.service.UnitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
