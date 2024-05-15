package com.sliit.ayu.ayuservice.model;

import com.sliit.ayu.ayuservice.dto.StockTransferItemDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.Date;

@Data
@Entity
@Table(name = "stock_transfer_item")
public class StockTransferItemEntity {
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name ="stock_transfer_id")
    private Integer stockTransferId;

    @Column(name ="medicine_id")
    private Integer medicineId;

    @Column(name ="qty")
    private Integer qty;

    @Column(name = "created_date", columnDefinition = "TIMESTAMP")
    private Date createdDate;

    @Column(name = "updated_date", columnDefinition = "TIMESTAMP")
    private Date updatedDate;

    public StockTransferItemDTO toDTO(){
        ModelMapper modelMapper = new ModelMapper();
        StockTransferItemDTO dto = new StockTransferItemDTO();
        modelMapper.map(this, dto);
        return dto;
    }
}
