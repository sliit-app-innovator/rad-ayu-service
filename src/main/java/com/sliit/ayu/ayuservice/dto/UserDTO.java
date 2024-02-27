package com.sliit.ayu.ayuservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sliit.ayu.ayuservice.model.UserEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;


@Data
@Valid
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "title", "first_name", "last_name", "employee_number", "designation", "user_role", "departmentId", "created_date","updated_date"})
public class UserDTO {

    private Integer id;

    @JsonProperty("title")
    @NotNull(message = "title cannot be null")
    @NotEmpty(message = "title cannot be null")
    private String title;

    @JsonProperty("first_name")
    @NotNull(message = "firstName cannot be null")
    @NotEmpty(message = "firstName cannot be null")
    private String firstName;

    @JsonProperty("last_name")
    @NotNull(message = "lastName cannot be null")
    @NotEmpty(message = "lastName cannot be null")
    private String lastName;

    @JsonProperty("employee_number")
    @NotNull(message = "employeeNumber cannot be null")
    @NotEmpty(message = "employeeNumber cannot be null")
    private String employeeNumber;

    @NotNull(message = "designation cannot be null")
    @NotEmpty(message = "designation cannot be null")
    private String designation;

    @JsonProperty("user_role")
    @NotNull(message = "userRole cannot be null")
    @NotEmpty(message = "userRole cannot be null")
    private String userRole;

    @JsonProperty("department_id")
    private  Integer departmentId;

    @JsonProperty("created_date")
    private Date createdDate;

    @JsonProperty("updated_date")
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
        return userEntity;
    }
}
