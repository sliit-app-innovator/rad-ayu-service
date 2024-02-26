package com.sliit.ayu.ayuservice.repository;

import com.sliit.ayu.ayuservice.model.UserEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer>, JpaSpecificationExecutor<UserEntity>  {
        UserEntity findByFirstNameAndLastName(String fname, String lname);
        UserEntity findByEmployeeNumber(String employeeNumber);
}
