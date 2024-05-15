package com.sliit.ayu.ayuservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Top5FastMovingItemsResDto {
    private int id;
    private String name;
    private int issued;


}
