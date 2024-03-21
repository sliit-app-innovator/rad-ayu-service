package com.sliit.ayu.ayuservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sliit.ayu.ayuservice.model.MedicineEntity;
import com.sliit.ayu.ayuservice.model.UserEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;


@Data
@Valid
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "name", "code", "type", "medicineType", "created_date","updated_date"})
public class MedicineDTO {

    private Integer id;

    @NotNull(message = "name cannot be null")
    @NotEmpty(message = "name cannot be null")
    private String name;

    private String code;
    private Integer type;

    @NotNull(message = "medicine_type cannot be null")
    @NotEmpty(message = "medicine_type cannot be null")
    @JsonProperty("medicineType")
    private Integer medicineType;

    @NotNull(message = "unit cannot be null")
    @NotEmpty(message = "unit cannot be null")
    @JsonProperty("unit")
    private Integer unit;

    @JsonProperty("created_date")
    private Date createdDate;

    @JsonProperty("updated_date")
    private Date updatedDate;

    public MedicineEntity toEntity() {
        MedicineEntity entity = new MedicineEntity();
        entity.setId(this.id);
        entity.setName(this.getName());
        entity.setCode(this.code);
        entity.setUnit(this.unit);
        entity.setType(this.type);
        entity.setMedicineType(this.medicineType);
        entity.setCreatedDate(this.createdDate);
        entity.setUpdatedDate(this.updatedDate);
        return entity;
    }
}
