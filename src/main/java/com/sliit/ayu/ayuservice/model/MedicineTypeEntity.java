package com.sliit.ayu.ayuservice.model;

import com.sliit.ayu.ayuservice.dto.MedicineTypeDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Data
@Entity
@Table(name = "medicine_type")
public class MedicineTypeEntity {

    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name ="name")
    private String name;

       @Column(name = "created_date", columnDefinition = "TIMESTAMP")
    private Date createdDate;

    @Column(name = "updated_date", columnDefinition = "TIMESTAMP")
    private Date updatedDate;

    public MedicineTypeDTO toDTO() {
        MedicineTypeDTO medicineDTO = new MedicineTypeDTO();
        medicineDTO.setId(this.id);
        medicineDTO.setName(this.getName());
        medicineDTO.setCreatedDate(this.createdDate);
        medicineDTO.setUpdatedDate(this.updatedDate);
        return medicineDTO;
    }
}
