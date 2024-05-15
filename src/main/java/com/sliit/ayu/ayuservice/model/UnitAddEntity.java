package com.sliit.ayu.ayuservice.model;
import com.sliit.ayu.ayuservice.dto.MedicineCategoryDTO;
import com.sliit.ayu.ayuservice.dto.UnitAddDTO;
import com.sliit.ayu.ayuservice.dto.UnitDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Data
@Entity
@Table(name = "unit")


public class UnitAddEntity {
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name ="unit",unique = true, nullable = false)
    private String unit;
    @Column(name ="code",unique = true, nullable = false)
    private String code;

    @Column(name = "created_date", columnDefinition = "TIMESTAMP")
    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    @Column(name = "updated_date", columnDefinition = "TIMESTAMP")
    private Date updatedDate;

    public UnitAddDTO toDTO() {
        UnitAddDTO dto = new UnitAddDTO();
        dto.setId(this.id);
        dto.setCode(this.code);
        dto.setUnit(this.unit);
        dto.setCreatedDate(this.createdDate);
        dto.setUpdatedDate(this.updatedDate);
        return dto;
}
}