package com.sliit.ayu.ayuservice.constants;

public enum TransactionDescriptions {
    AU_STOCK_RECEIVE("STOCK_RETRIEVE"),
    AU_STOCK_TRANSFER_OUT("STOCK_TRANSFER_OUT"),
    AU_STOCK_TRANSFER_IN("STOCK_TRANSFER_IN"),
    AU_STOCK_ISSUE_TO_PATIENT("STOCK_ISSUE_TO_PATIENT");

    String description;


    TransactionDescriptions(String description) {
        this.description = description;
    }

    public String getCode(){
        return this.name().toUpperCase();
    }

    public String getDescription(){
        return this.description;
    }
}
