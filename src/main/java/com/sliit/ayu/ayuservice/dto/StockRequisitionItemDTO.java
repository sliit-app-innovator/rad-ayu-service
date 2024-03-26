package com.sliit.ayu.ayuservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sliit.ayu.ayuservice.model.StockRequisitionItemEntity;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.Date;

@Data
public class StockRequisitionItemDTO {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("stock_requisition_id")
    private Integer stockRequisitionId;

    @JsonProperty("medicine_id")
    private Integer medicineId;

    @JsonProperty("qty")
    private Integer qty;

    @JsonProperty("created_date")
    private Date createdDate;

    @JsonProperty("updated_date")
    private Date updatedDate;

    public StockRequisitionItemEntity toEntity(){
        ModelMapper modelMapper = new ModelMapper();
        StockRequisitionItemEntity entity = new StockRequisitionItemEntity();
        modelMapper.map(this, entity);
        return entity;
    }
}
