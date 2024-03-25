package com.sliit.ayu.ayuservice.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigInteger;
import java.util.Date;

@Data
@Entity
@Table(name = "medicine_movement")
public class MedicineMovementEntity {
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name ="medicine_id")
    private Integer medicineId;

    @Column(name ="reference_id")
    private Integer referenceId;

    @Column(name ="lot_id")
    private Integer lotId;

    @Column(name ="store_id")
    private Integer storeId;

    @Column(name ="in_qty")
    private Integer inQty;

    @Column(name ="out_qty")
    private Integer outQty;

    @Column(name ="description")
    private String description;

    @Column(name = "created_date", columnDefinition = "TIMESTAMP")
    private Date createdDate;

    @Column(name = "updated_date", columnDefinition = "TIMESTAMP")
    private Date updatedDate;

}
