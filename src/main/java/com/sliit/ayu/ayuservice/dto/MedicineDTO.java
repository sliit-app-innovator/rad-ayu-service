package com.sliit.ayu.ayuservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sliit.ayu.ayuservice.model.MedicineEntity;
import com.sliit.ayu.ayuservice.model.UserEntity;
import jakarta.persistence.Transient;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
@Valid
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "name", "code", "type", "medicineType", "created_date","updated_date","isExpire"})
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

    @JsonProperty("uname")
    @Transient
    private String uname;

    @JsonProperty("created_date")
    private Date createdDate;

    @JsonProperty("updated_date")
    private Date updatedDate;

    @JsonProperty("isExpire")
    private Boolean isExpire;

    @JsonProperty("reorderLevel")
    private Integer reorderLevel;

    public MedicineDTO(){}
    public MedicineDTO(Integer id, String name, String code, Integer type, Integer medicineType, Integer unit, String uname, Date createdDate, Date updatedDate, Boolean isExpire,Integer reorderLevel) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.type = type;
        this.medicineType = medicineType;
        this.unit = unit;
        this.uname = uname;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.isExpire = isExpire;
        this.reorderLevel = reorderLevel;
    }

      public MedicineDTO(MedicineEntity entity) {
            this.id = entity.getId();
            this.name = entity.getName();
            this.code = entity.getCode();
            this.type = entity.getType();
            this.medicineType = entity.getMedicineType();
            this.unit = entity.getUnit();
            this.createdDate = entity.getCreatedDate();
            this.updatedDate = entity.getUpdatedDate();
            this.isExpire = entity.getIsExpire();
            this.reorderLevel = entity.getReorderLevel();
        }

    public MedicineEntity toEntity() {
        MedicineEntity entity = new MedicineEntity();
        entity.setId(this.id);
        entity.setName(this.getName());
        entity.setCode(this.code);
        entity.setUnit(this.unit);
        entity.setType(this.type);
        entity.setIsExpire(this.isExpire);
        entity.setReorderLevel(this.reorderLevel);
        entity.setMedicineType(this.medicineType);
        entity.setCreatedDate(this.createdDate);
        entity.setUpdatedDate(this.updatedDate);
        return entity;
    }
}
