package com.sliit.ayu.ayuservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sliit.ayu.ayuservice.model.UserEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;



@Data
@Valid
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserUpdateDTO {

    private Integer id;

    @JsonProperty("title")
    @NotEmpty(message = "title cannot be null")
    private String title;

    @JsonProperty("first_name")
    @NotEmpty(message = "firstName cannot be null")
    private String firstName;

    @JsonProperty("last_name")
    @NotEmpty(message = "lastName cannot be null")
    private String lastName;

    @JsonProperty("employee_number")
    @NotEmpty(message = "employeeNumber cannot be null")
    private String employeeNumber;

    @NotEmpty(message = "designation cannot be null")
    private String designation;

    @JsonProperty("user_role")
    @NotEmpty(message = "userRole cannot be null")
    private String userRole;

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
        return userEntity;
    }
}
