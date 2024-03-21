package com.sliit.ayu.ayuservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sliit.ayu.ayuservice.model.MedicineCategoryEntity;
import com.sliit.ayu.ayuservice.model.UnitEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
@Valid
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "unit", "code", "created_date","updated_date"})
public class UnitDTO {
    private Integer id;

    @NotNull(message = "unit cannot be null")
    @NotEmpty(message = "unit cannot be null")
    private String unit;

    @NotNull(message = "unit code cannot be null")
    @NotEmpty(message = "code code cannot be null")
    private String code;

    @JsonProperty("created_date")
    private Date createdDate;

    @JsonProperty("updated_date")
    private Date updatedDate;

    public UnitEntity toEntity() {
        UnitEntity entity = new UnitEntity();
        entity.setId(this.id);
        entity.setCode(this.code);
        entity.setUnit(this.unit);
        entity.setCreatedDate(this.createdDate);
        entity.setUpdatedDate(this.updatedDate);
        return entity;
    }
}
