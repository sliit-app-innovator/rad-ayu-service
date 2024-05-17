package com.sliit.ayu.ayuservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sliit.ayu.ayuservice.model.StockRequisitionItemEntity;
import com.sliit.ayu.ayuservice.model.StockTransferItemEntity;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.Date;
import java.util.List;

@Data
public class StockTransferItemDTO {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("stock_transfer_id")
    private Integer stockTransferId;

    @JsonProperty("medicine_id")
    private Integer medicineId;

    @JsonProperty("qty")
    private Integer qty;

    @JsonProperty("is_lot")
    private Boolean isLot;

    private List<TransferLotDTO> lots;

    @JsonProperty("created_date")
    private Date createdDate;

    @JsonProperty("updated_date")
    private Date updatedDate;

    public StockTransferItemEntity toEntity(){
        ModelMapper modelMapper = new ModelMapper();
        StockTransferItemEntity entity = new StockTransferItemEntity();
        modelMapper.map(this, entity);
        return entity;
    }
}
