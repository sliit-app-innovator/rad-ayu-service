package com.sliit.ayu.ayuservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sliit.ayu.ayuservice.model.UserEntity;
import com.sliit.ayu.ayuservice.model.WardEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Valid
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WardUpdateDTO {
    private Integer id;

    @JsonProperty("wardNumber")
    @NotEmpty(message = "ward number cannot be null")
    private Integer wardNumber;

    @NotEmpty(message = "description cannot be null")
    private String description;

    @NotEmpty(message = "typeId cannot be null")
    private Integer typeId;

    private  Integer capacity;

    public WardEntity toEntity() {
        WardEntity wardEntity = new WardEntity();
        wardEntity.setWardNumber(this.wardNumber);
        wardEntity.setDescription(this.description);
        wardEntity.setTypeId(this.typeId);
        wardEntity.setCapacity(this.capacity);

        return wardEntity;
    }
}
