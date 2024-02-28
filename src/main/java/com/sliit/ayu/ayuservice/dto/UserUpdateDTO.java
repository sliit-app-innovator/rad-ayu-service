package com.sliit.ayu.ayuservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sliit.ayu.ayuservice.model.UserEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;



@Data
@Valid
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserUpdateDTO {

    private Integer id;

    @JsonProperty("title")
    @NotEmpty(message = "title cannot be null")
    private String title;

    @NotEmpty(message = "firstName cannot be null")
    private String firstName;

    @NotEmpty(message = "lastName cannot be null")
    private String lastName;

    @NotEmpty(message = "employeeNumber cannot be null")
    private String employeeNumber;

    @NotEmpty(message = "designation cannot be null")
    private String designation;

    @NotEmpty(message = "userRole cannot be null")
    private String userRole;

    @NotNull(message = "username cannot be null")
    @NotEmpty(message = "username cannot be null")
    private String username;

    @NotNull(message = "password cannot be null")
    @NotEmpty(message = "password cannot be null")
    private String password;


    private  Integer departmentId;

    public UserEntity toEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(this.firstName);
        userEntity.setLastName(this.lastName);
        userEntity.setTitle(this.title);
        userEntity.setDesignation(this.designation);
        userEntity.setUserRole(this.userRole);
        userEntity.setDepartmentId(this.departmentId);
        userEntity.setEmployeeNumber(this.employeeNumber);
        userEntity.setUsername(this.username);
        userEntity.setPassword(this.password);
        return userEntity;
    }
}
