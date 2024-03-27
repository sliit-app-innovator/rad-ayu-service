package com.sliit.ayu.ayuservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sliit.ayu.ayuservice.model.MedicineEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;


@Data
@Valid
public class MedicineUpdateDTO {

    private Integer id;

    private String name;

    private String code;
    private Integer type;
    private Integer unit;

    @JsonProperty("medicine_type")
    private Integer medicineType;

    private Boolean isExpire;

    public MedicineEntity toEntity() {
        MedicineEntity entity = new MedicineEntity();
        entity.setId(this.id);
        entity.setName(this.getName());
        entity.setType(this.type);
        entity.setUnit(this.unit);
        entity.setMedicineType(this.medicineType);
        return entity;
    }
}
