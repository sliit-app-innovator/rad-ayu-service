package com.sliit.ayu.ayuservice.dto;

import com.sliit.ayu.ayuservice.model.StockRequisitionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockRequestDTO {
    private Integer id;
    private Integer fromStoreId;
    private Integer toStoreId;
    private String requestedBy;
    private String approvedBy;
    private String description;
    private int status;
    private String createdDate;
    private String updatedDate;
    private String reference;

    public StockRequestDTO(StockRequisitionEntity stockRequisitionEntity) {
        this.id = stockRequisitionEntity.getId();
        this.fromStoreId = stockRequisitionEntity.getFromStore();
        this.toStoreId = stockRequisitionEntity.getToStore();
        this.requestedBy = stockRequisitionEntity.getRequestedBy();
        this.approvedBy = stockRequisitionEntity.getApprovedBy();
        this.description = stockRequisitionEntity.getDescription();
        this.status = stockRequisitionEntity.getStatusId();
        this.createdDate = stockRequisitionEntity.getCreatedDate().toString();
        this.updatedDate = stockRequisitionEntity.getUpdatedDate().toString();
        this.reference = stockRequisitionEntity.getReference();
    }
}
