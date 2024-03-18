package com.sliit.ayu.ayuservice.constants;

public enum ErrorCode {
    AU_001("Entity cannot be found"),
    AU_002("Duplicate Entry");

    String errorMessage;
    ErrorCode(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getCode(){
        return this.name().toUpperCase();
    }

    public String getMessage(){
        return this.errorMessage;
    }
}
