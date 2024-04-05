package com.sliit.ayu.ayuservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "medicine_issue_details")
public class MedicineIssueDetailEntity {
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name ="medicine_issue_id")
    private Integer medicineIssueId;

    @Column(name ="medicine_id")
    private Integer medicineId;

    @Column(name ="qty")
    private Integer qty;

    @Column(name = "created_date", columnDefinition = "TIMESTAMP")
    private Date createdDate;

    @Column(name = "updated_date", columnDefinition = "TIMESTAMP")
    private Date updatedDate;

}
