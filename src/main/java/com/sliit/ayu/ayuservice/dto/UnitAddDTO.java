package com.sliit.ayu.ayuservice.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sliit.ayu.ayuservice.model.UnitAddEntity;

import com.sliit.ayu.ayuservice.model.UnitEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UnitAddDTO {

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
        UnitEntity unitentity = new UnitEntity();
        unitentity.setId(this.id);
        unitentity.setCode(this.code);
        unitentity.setUnit(this.unit);
        unitentity.setCreatedDate(this.createdDate);
        unitentity.setUpdatedDate(this.updatedDate);
        return unitentity;
    }
}
}
