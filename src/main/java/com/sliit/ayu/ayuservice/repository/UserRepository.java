package com.sliit.ayu.ayuservice.repository;

import com.sliit.ayu.ayuservice.dto.UserDTO;
import com.sliit.ayu.ayuservice.model.UserEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer>, JpaSpecificationExecutor<UserEntity>  {

        UserEntity findByFirstNameAndLastName(String fname, String lname);
        UserEntity findByEmployeeNumber(String employeeNumber);


        @Transactional
        @Query(value =DbQuery.GET_ALL_USERS, nativeQuery = true)
        List<UserEntity> getAll();

        @Transactional
        @Query(value =DbQuery.SEARCH_USER_BY_NAME_QUERY, nativeQuery = true)
        List<UserEntity> findByFirstNameOrLastName(String firstName, String lastName);

        UserEntity findByUsername(String username);
}
