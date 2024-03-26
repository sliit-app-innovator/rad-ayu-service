package com.sliit.ayu.ayuservice.constants;

public enum OrderStatus {
    NEW(1),
    OPEN(2),
    ACCEPTED(3),
    PARTIALLY_ACCEPTED(4),
    REJECTED(5),
    UNKNOWN(0);

    final int id;

    OrderStatus(int id) {
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public static OrderStatus getById(int id) {
        for(OrderStatus e : values()) {
            if(e.id == id)
                return e;
        }
        return UNKNOWN;
    }
}
