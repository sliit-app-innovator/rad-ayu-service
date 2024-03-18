package com.sliit.ayu.ayuservice.model;

import com.sliit.ayu.ayuservice.dto.MedicineDTO;
import com.sliit.ayu.ayuservice.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Data
@Entity
@Table(name = "medicine")
public class MedicineEntity {

    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name ="name")
    private String name;

    @Column(name ="code")
    private String code;

    @Column(name ="type")
    private Integer type;

    @Column(name ="medicine_type",unique = true)
    private Integer medicineType;

    @Column(name ="designation")
    private String designation;

    @Column(name = "created_date", columnDefinition = "TIMESTAMP")
    private Date createdDate;

    @Column(name = "updated_date", columnDefinition = "TIMESTAMP")
    private Date updatedDate;

    public MedicineDTO toDTO() {
        MedicineDTO medicineDTO = new MedicineDTO();
        medicineDTO.setId(this.id);
        medicineDTO.setName(this.getName());
        medicineDTO.setType(this.type);
        medicineDTO.setMedicineType(this.medicineType);
        medicineDTO.setCreatedDate(this.createdDate);
        medicineDTO.setUpdatedDate(this.updatedDate);
        return medicineDTO;
    }
}
