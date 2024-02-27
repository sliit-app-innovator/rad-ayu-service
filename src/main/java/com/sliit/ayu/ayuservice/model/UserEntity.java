package com.sliit.ayu.ayuservice.model;

import com.sliit.ayu.ayuservice.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Data
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name ="title")
    private String title;

    @Column(name ="first_name")
    private String firstName;

    @Column(name ="last_name")
    private String lastName;

    @Column(name ="employee_number",unique = true)
    private String employeeNumber;

    @Column(name ="designation")
    private String designation;

    @Column(name ="user_role")
    private String userRole;

    @Column(name ="department_id")
    private  Integer departmentId;

    @Column(name = "created_date", columnDefinition = "TIMESTAMP")
    private Date createdDate;

    @Column(name = "updated_date", columnDefinition = "TIMESTAMP")
    private Date updatedDate;

    @Column(unique = true ,nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    public UserDTO toDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(this.id);
        userDTO.setFirstName(this.firstName);
        userDTO.setLastName(this.lastName);
        userDTO.setTitle(this.title);
        userDTO.setDesignation(this.designation);
        userDTO.setUserRole(this.userRole);
        userDTO.setDepartmentId(this.departmentId);
        userDTO.setCreatedDate(this.createdDate);
        userDTO.setUpdatedDate(this.updatedDate);
        userDTO.setUsername(this.username);
        return userDTO;
    }
}
