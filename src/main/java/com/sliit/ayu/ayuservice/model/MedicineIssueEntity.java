package com.sliit.ayu.ayuservice.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "medicine_issue")
public class MedicineIssueEntity {
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name ="issue_type")
    private Integer issueType;

    @Column(name ="date")
    private Date date;

    @Column(name ="description")
    private String description;

    @Column(name ="doctor")
    private String  doctor;

    @Column(name ="patient")
    private String patient;

    @Column(name ="store_id")
    private Integer  storeId;

    @Column(name = "created_date", columnDefinition = "TIMESTAMP")
    private Date createdDate;

    @Column(name = "updated_date", columnDefinition = "TIMESTAMP")
    private Date updatedDate;

}
