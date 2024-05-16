package com.sliit.ayu.ayuservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardWidgetResDto {
    private int total;
    private int pending;
    private int completed;
    private double completedRate;
}
