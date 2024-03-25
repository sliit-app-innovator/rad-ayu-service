package com.sliit.ayu.ayuservice.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "medicine_lot")
public class MedicineLotEntity {
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name ="medicine_id")
    private Integer medicineId;

    @Column(name ="lot_num")
    private String lotNum;

    @Column(name ="qty")
    private Integer qty;

    @Column(name = "expire_date")
    private Date expireDate;

    @Column(name = "created_date", columnDefinition = "TIMESTAMP")
    private Date createdDate;

    @Column(name = "updated_date", columnDefinition = "TIMESTAMP")
    private Date updatedDate;

}
