package com.sliit.ayu.ayuservice.model;

import com.sliit.ayu.ayuservice.dto.StoreTypeDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Data
@Entity
@Table(name = "store_type")
public class StoreTypeEntity {
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name ="name")
    private String name;

    @CreatedDate
    @Column(name = "created_date", columnDefinition = "TIMESTAMP")
    private Date createdDate;

    @LastModifiedDate
    @Column(name = "updated_date", columnDefinition = "TIMESTAMP")
    private Date updatedDate;

    public StoreTypeDTO toDTO() {
        StoreTypeDTO storeTypeDTO = new StoreTypeDTO();
        storeTypeDTO.setId(this.id);
        storeTypeDTO.setName(this.name);
        storeTypeDTO.setCreatedDate(this.createdDate);
        storeTypeDTO.setUpdatedDate(this.updatedDate);
        return storeTypeDTO;
    }
}
