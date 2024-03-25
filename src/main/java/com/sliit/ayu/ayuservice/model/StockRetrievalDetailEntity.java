package com.sliit.ayu.ayuservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "stock_retrieval_detail")
public class StockRetrievalDetailEntity {
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name ="stock_retrieval_id")
    private Integer stockRetrievalId;

    @Column(name ="medicine_id")
    private Integer medicineId;

    @Column(name ="qty")
    private Integer qty;

    @Column(name = "created_date", columnDefinition = "TIMESTAMP")
    private Date createdDate;

    @Column(name = "updated_date", columnDefinition = "TIMESTAMP")
    private Date updatedDate;

}
