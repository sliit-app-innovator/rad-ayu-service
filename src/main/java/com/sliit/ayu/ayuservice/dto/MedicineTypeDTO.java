package com.sliit.ayu.ayuservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sliit.ayu.ayuservice.model.MedicineTypeEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;


@Data
@Valid
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "name", "code", "created_date","updated_date"})
public class MedicineTypeDTO {

    private Integer id;

    @NotNull(message = "name cannot be null")
    @NotEmpty(message = "name cannot be null")
    private String name;

    @JsonProperty("created_date")
    private Date createdDate;

    @JsonProperty("updated_date")
    private Date updatedDate;

    public MedicineTypeEntity toEntity() {
        MedicineTypeEntity entity = new MedicineTypeEntity();
        entity.setId(this.id);
        entity.setName(this.getName());
        entity.setCreatedDate(this.createdDate);
        entity.setUpdatedDate(this.updatedDate);
        return entity;
    }
}
