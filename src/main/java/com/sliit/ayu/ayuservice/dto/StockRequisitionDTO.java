package com.sliit.ayu.ayuservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sliit.ayu.ayuservice.constants.OrderStatus;
import com.sliit.ayu.ayuservice.model.StockRequisitionEntity;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.Date;

@Data
public class StockRequisitionDTO {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("reference")
    private String reference;

    @JsonProperty("date")
    private Date date;

    @JsonProperty("from_store")
    private Integer fromStore;

    @JsonProperty("to_store")
    private Integer toStore;

    @JsonProperty("requested_by")
    private String requestedBy;

    @JsonProperty("approved_by")
    private String approvedBy;

    @JsonProperty("description")
    private String description;

    @JsonProperty("status")
    private String status;

    @JsonProperty("created_date")
    private Date createdDate;

    @JsonProperty("updated_date")
    private Date updatedDate;

    public StockRequisitionEntity toEntity(){
        ModelMapper modelMapper = new ModelMapper();
        StockRequisitionEntity entity = new StockRequisitionEntity();
        modelMapper.map(this, entity);
        entity.setStatusId(OrderStatus.valueOf(this.status).getId());
        return entity;
    }
}
