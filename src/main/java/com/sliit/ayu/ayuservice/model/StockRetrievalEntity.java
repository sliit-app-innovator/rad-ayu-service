package com.sliit.ayu.ayuservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "stock_retrieval")
public class StockRetrievalEntity {
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date")
    private Date date;

    @Column(name = "source")
    private String source;

    @Column(name = "store_id")
    private Integer storeId;

    @Column(name = "request_by")
    private String requestBy;

    @Column(name = "approved_by")
    private String approvedBy;

    @Column(name = "reference")
    private String reference;

    @Column(name = "description")
    private String description;

    @Column(name = "created_date", columnDefinition = "TIMESTAMP")
    private Date createdDate;

    @Column(name = "updated_date", columnDefinition = "TIMESTAMP")
    private Date updatedDate;

}
