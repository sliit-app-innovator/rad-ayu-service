package com.sliit.ayu.ayuservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sliit.ayu.ayuservice.model.UserEntity;
import com.sliit.ayu.ayuservice.model.WardEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
@Valid
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "title", "first_name", "last_name", "employee_number", "designation", "user_role", "departmentId", "created_date","updated_date"})
public class WardDTO {
    private Integer id;

    @NotNull(message = "wardNumber cannot be null")
    @NotEmpty(message = "wardNumber cannot be null")
    private Integer wardNumber;

    @NotNull(message = "description cannot be null")
    @NotEmpty(message = "description cannot be null")
    private String description;

    @NotNull(message = "typeId cannot be null")
    @NotEmpty(message = "typeId cannot be null")
    private Integer typeId;

    private  Integer capacity;

    @JsonProperty("created_date")
    private Date createdDate;

    @JsonProperty("updated_date")
    private Date updatedDate;

    public WardEntity toEntity() {
        WardEntity wardEntity = new WardEntity();
        wardEntity.setWardNumber(this.wardNumber);
        wardEntity.setDescription(this.description);
        wardEntity.setTypeId(this.typeId);
        wardEntity.setCapacity(this.capacity);
        wardEntity.setCreatedDate(this.createdDate);
        wardEntity.setUpdatedDate(this.updatedDate);

        return wardEntity;
    }
}
