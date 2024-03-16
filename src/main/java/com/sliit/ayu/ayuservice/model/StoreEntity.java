package com.sliit.ayu.ayuservice.model;

import com.sliit.ayu.ayuservice.dto.StoreDTO;
import com.sliit.ayu.ayuservice.dto.StoreTypeDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Data
@Entity
@Table(name = "store")
public class StoreEntity {
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name ="name")
    private String name;

    @NotNull(message = "store type cannot be null")
    @NotEmpty(message = "store type cannot be null")
    private int type;


    @Column(name = "created_date", columnDefinition = "TIMESTAMP")
    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    @Column(name = "updated_date", columnDefinition = "TIMESTAMP")
    private Date updatedDate;

    public StoreDTO toDTO() {
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setId(this.id);
        storeDTO.setType(this.id);
        storeDTO.setName(this.name);
        storeDTO.setCreatedDate(this.createdDate);
        storeDTO.setUpdatedDate(this.updatedDate);
        return storeDTO;
    }
}
