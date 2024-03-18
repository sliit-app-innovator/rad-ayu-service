package com.sliit.ayu.ayuservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sliit.ayu.ayuservice.model.StoreEntity;
import com.sliit.ayu.ayuservice.model.UserEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
@Valid
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "name",  "created_date","updated_date"})
public class StoreDTO {
    private Integer id;

    @NotNull(message = "store type cannot be null")
    @NotEmpty(message = "store type cannot be null")
    private String name;

    @NotNull(message = "store type cannot be null")
    @NotEmpty(message = "store type cannot be null")
    private int type;

    @JsonProperty("created_date")
    private Date createdDate;

    @JsonProperty("updated_date")
    private Date updatedDate;

    public StoreEntity toEntity() {
        StoreEntity storeEntity = new StoreEntity();
        storeEntity.setId(this.id);
        storeEntity.setName(this.name);
        storeEntity.setCreatedDate(this.createdDate);
        storeEntity.setUpdatedDate(this.updatedDate);
        return storeEntity;
    }

}
