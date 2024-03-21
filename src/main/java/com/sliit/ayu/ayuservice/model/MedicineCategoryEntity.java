package com.sliit.ayu.ayuservice.model;

import com.sliit.ayu.ayuservice.dto.MedicineCategoryDTO;
import com.sliit.ayu.ayuservice.dto.StoreTypeDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Data
@Entity
@Table(name = "medicine_type")
public class MedicineCategoryEntity {
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name ="name",unique = true , nullable = false)
    private String name;

    @Column(name = "created_date", columnDefinition = "TIMESTAMP")
    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    @Column(name = "updated_date", columnDefinition = "TIMESTAMP")
    private Date updatedDate;


    public MedicineCategoryDTO toDTO() {
        MedicineCategoryDTO dto = new MedicineCategoryDTO();
        dto.setId(this.id);
        dto.setName(this.name);
        dto.setCreatedDate(this.createdDate);
        dto.setUpdatedDate(this.updatedDate);
        return dto;
    }
}
