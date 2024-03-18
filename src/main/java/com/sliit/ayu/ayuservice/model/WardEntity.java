package com.sliit.ayu.ayuservice.model;

import com.sliit.ayu.ayuservice.dto.UserDTO;
import com.sliit.ayu.ayuservice.dto.WardDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "ward")
public class WardEntity {
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name ="ward_number")
    private int wardNumber;

    @Column(name ="description")
    private String description;

    @Column(name ="type_id")
    private int typeId;

    @Column(name ="capacity")
    private  Integer capacity;

    @Column(name = "created_date", columnDefinition = "TIMESTAMP")
    private Date createdDate;

    @Column(name = "updated_date", columnDefinition = "TIMESTAMP")
    private Date updatedDate;

    public WardDTO toDTO() {
        WardDTO wardDTO = new WardDTO();
        wardDTO.setId(this.id);
        wardDTO.setWardNumber(this.wardNumber);
        wardDTO.setDescription(this.description);
        wardDTO.setTypeId(this.typeId);
        wardDTO.setCapacity(this.capacity);
        wardDTO.setCreatedDate(this.createdDate);
        wardDTO.setUpdatedDate(this.updatedDate);

        return wardDTO;
    }
}
