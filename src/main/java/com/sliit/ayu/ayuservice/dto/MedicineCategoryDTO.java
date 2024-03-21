package com.sliit.ayu.ayuservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sliit.ayu.ayuservice.model.MedicineCategoryEntity;
import com.sliit.ayu.ayuservice.model.StoreEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
@Data
@Valid
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "name","created_date","updated_date"})
public class MedicineCategoryDTO {
    private Integer id;

    @NotNull(message = "medicine category cannot be null")
    @NotEmpty(message = "category category cannot be null")
    private String name;

    @JsonProperty("created_date")
    private Date createdDate;

    @JsonProperty("updated_date")
    private Date updatedDate;


    public MedicineCategoryEntity toEntity() {
        MedicineCategoryEntity entity = new MedicineCategoryEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setCreatedDate(this.createdDate);
        entity.setUpdatedDate(this.updatedDate);
        return entity;
    }
}
