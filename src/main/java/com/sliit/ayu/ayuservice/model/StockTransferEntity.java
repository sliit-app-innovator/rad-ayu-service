package com.sliit.ayu.ayuservice.model;

import com.sliit.ayu.ayuservice.constants.OrderStatus;
import com.sliit.ayu.ayuservice.dto.StockRequisitionDTO;
import com.sliit.ayu.ayuservice.dto.StockTransferDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.Date;

@Data
@Entity
@Table(name = "stock_transfer")
public class StockTransferEntity {
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date")
    private Date date;

    @Column(name = "from_store")
    private Integer fromStore;

    @Column(name = "to_store")
    private Integer toStore;

    @Column(name = "requested_by")
    private String requestedBy;

    @Column(name = "approved_by")
    private String approvedBy;

    @Column(name = "reference")
    private String reference;

    @Column(name = "statusId")
    private int statusId;

    @Column(name = "description")
    private String description;

    @Column(name = "created_date", columnDefinition = "TIMESTAMP")
    private Date createdDate;

    @Column(name = "updated_date", columnDefinition = "TIMESTAMP")
    private Date updatedDate;

    public StockTransferDTO toDTO(){
        ModelMapper modelMapper = new ModelMapper();
        StockTransferDTO dto = new StockTransferDTO();
        modelMapper.map(this, dto);
        dto.setStatus(OrderStatus.getById(getStatusId()).name().toUpperCase());
        return dto;
    }
}
