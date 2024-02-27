package com.sliit.ayu.ayuservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sliit.ayu.ayuservice.model.UserEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;


@Data
@Valid
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private Integer id;

    @NotNull(message = "title cannot be null")
    @NotEmpty(message = "title cannot be null")
    private String title;

    @NotNull(message = "firstName cannot be null")
    @NotEmpty(message = "firstName cannot be null")
    private String firstName;

    @NotNull(message = "lastName cannot be null")
    @NotEmpty(message = "lastName cannot be null")
    private String lastName;

    @NotNull(message = "employeeNumber cannot be null")
    @NotEmpty(message = "employeeNumber cannot be null")
    private String employeeNumber;

    @NotNull(message = "designation cannot be null")
    @NotEmpty(message = "designation cannot be null")
    private String designation;


    @NotNull(message = "userRole cannot be null")
    @NotEmpty(message = "userRole cannot be null")
    private String userRole;

    @NotNull(message = "username cannot be null")
    @NotEmpty(message = "username cannot be null")
    private String username;

    @NotNull(message = "password cannot be null")
    @NotEmpty(message = "password cannot be null")
    private String password;

    private  Integer departmentId;

    private Date createdDate;
    private Date updatedDate;

    public UserEntity toEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(this.firstName);
        userEntity.setLastName(this.lastName);
        userEntity.setTitle(this.title);
        userEntity.setDesignation(this.designation);
        userEntity.setUserRole(this.userRole);
        userEntity.setDepartmentId(this.departmentId);
        userEntity.setEmployeeNumber(this.employeeNumber);
        userEntity.setCreatedDate(this.createdDate);
        userEntity.setUpdatedDate(this.updatedDate);
        userEntity.setUsername(this.username);
        userEntity.setPassword(this.password);

        return userEntity;
    }
}
