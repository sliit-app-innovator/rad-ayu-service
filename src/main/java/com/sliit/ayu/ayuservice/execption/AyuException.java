package com.sliit.ayu.ayuservice.execption;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AyuException {
    private String errorCode;
    private String errorMessage;
}
