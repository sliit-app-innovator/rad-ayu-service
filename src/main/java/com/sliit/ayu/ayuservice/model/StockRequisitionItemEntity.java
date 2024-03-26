package com.sliit.ayu.ayuservice.model;

import com.sliit.ayu.ayuservice.dto.StockRequisitionDTO;
import com.sliit.ayu.ayuservice.dto.StockRequisitionItemDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.Date;

@Data
@Entity
@Table(name = "stock_requisition_item")
public class StockRequisitionItemEntity {
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name ="stock_requisition_id")
    private Integer stockRequisitionId;

    @Column(name ="medicine_id")
    private Integer medicineId;

    @Column(name ="qty")
    private Integer qty;

    @Column(name = "created_date", columnDefinition = "TIMESTAMP")
    private Date createdDate;

    @Column(name = "updated_date", columnDefinition = "TIMESTAMP")
    private Date updatedDate;

    public StockRequisitionItemDTO toDTO(){
        ModelMapper modelMapper = new ModelMapper();
        StockRequisitionItemDTO dto = new StockRequisitionItemDTO();
        modelMapper.map(this, dto);
        return dto;
    }
}
