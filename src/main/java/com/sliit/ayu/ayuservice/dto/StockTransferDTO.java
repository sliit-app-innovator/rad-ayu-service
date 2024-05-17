package com.sliit.ayu.ayuservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sliit.ayu.ayuservice.constants.OrderStatus;
import com.sliit.ayu.ayuservice.model.StockRequisitionEntity;
import com.sliit.ayu.ayuservice.model.StockTransferEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Valid
public class StockTransferDTO {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("reference")
    private String reference;

    @JsonProperty("requestNo")
    private Integer orderId;

    @JsonProperty("date")
    private Date date;

    @NotNull(message = "from_store cannot be null")
    @NotEmpty(message = "from_store cannot be null")
    @JsonProperty("from_store")
    private Integer fromStore;

    @JsonProperty("to_store")
    @NotNull(message = "toStore cannot be null")
    @NotEmpty(message = "toStore cannot be null")
    private Integer toStore;

    @JsonProperty("requested_by")
    @NotNull(message = "requestedBy cannot be null")
    @NotEmpty(message = "requestedBy cannot be null")
    private String requestedBy;

    @JsonProperty("approved_by")
    @NotNull(message = "approvedBy cannot be null")
    @NotEmpty(message = "approvedBy cannot be null")
    private String approvedBy;

    @JsonProperty("description")
    private String description;

    @JsonProperty("status")
    @NotNull(message = "status cannot be null")
    @NotEmpty(message = "status cannot be null")
    private String status;

    @JsonProperty("created_date")
    private Date createdDate;

    @JsonProperty("updated_date")
    private Date updatedDate;

    @JsonProperty("items")
    private List<StockTransferItemDTO> items;

    public StockTransferEntity toEntity(){
        ModelMapper modelMapper = new ModelMapper();
        StockTransferEntity entity = new StockTransferEntity();
        modelMapper.map(this, entity);
        entity.setStatusId(OrderStatus.valueOf(this.status).getId());
        return entity;
    }
}
