package com.sliit.ayu.ayuservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sliit.ayu.ayuservice.dto.UserDTO;
import com.sliit.ayu.ayuservice.dto.UserUpdateDTO;
import com.sliit.ayu.ayuservice.execption.AyuException;
import com.sliit.ayu.ayuservice.model.UserEntity;
import com.sliit.ayu.ayuservice.repository.UserRepository;
import com.sliit.ayu.ayuservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService  {

    public static final String AU_001 = "AU001";
    public static final String USER_CANNOT_BE_FOUND = "User cannot be found";
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        userDTO.setCreatedDate(Calendar.getInstance().getTime());
        userDTO.setUpdatedDate(Calendar.getInstance().getTime());
        userRepository.save(userDTO.toEntity());
        UserEntity userEntity = userRepository.findByEmployeeNumber(userDTO.getEmployeeNumber());
        return userEntity.toDTO();
    }

    @Override
    public List<UserDTO> searchUser(String name) {
        List<UserDTO> entityList = new ArrayList<>();
        if (name == null || name.isEmpty()) {
           userRepository.findAll().forEach(userEntity -> entityList.add(userEntity.toDTO()));
        } else {
            userRepository.findByFirstNameOrLastName(name, name).forEach(userEntity -> entityList.add(userEntity.toDTO()));
        }
        return entityList;
    }

    @Override
    public UserDTO getUser(int id) {
        Optional<UserEntity> optional = userRepository.findById(id);
        if(optional.isPresent()){
            return optional.get().toDTO();
        } else {
           throw AyuException.builder().errorCode(AU_001).errorMessage(USER_CANNOT_BE_FOUND).build();
        }
    }

    @Override
    public UserDTO updateUser(UserUpdateDTO userDTO) {
        Optional<UserEntity> optional = userRepository.findById(userDTO.getId());
        if (optional.isPresent()) {
            UserEntity userEntity = optional.get();
            if (userDTO.getFirstName() != null && !userDTO.getFirstName().isEmpty()) {
                userEntity.setFirstName(userDTO.getFirstName());
            }
            if (userDTO.getLastName() != null && !userDTO.getLastName().isEmpty()) {
                userEntity.setLastName(userDTO.getLastName());
            }
            if (userDTO.getTitle() != null && !userDTO.getTitle().isEmpty()) {
                userEntity.setTitle(userDTO.getTitle());
            }
            if (userDTO.getDesignation() != null && !userDTO.getDesignation().isEmpty()) {
                userEntity.setDesignation(userDTO.getDesignation());
            }
            if (userDTO.getEmployeeNumber() != null && !userDTO.getEmployeeNumber().isEmpty()) {
                userEntity.setEmployeeNumber(userDTO.getEmployeeNumber());
            }
            if (userDTO.getUserRole() != null && !userDTO.getUserRole().isEmpty()) {
                userEntity.setUserRole(userDTO.getUserRole());
            }
            userEntity.setUpdatedDate(new Date());
            userRepository.save(userEntity);
            optional = userRepository.findById(userDTO.getId());
            if (optional.isPresent()) {
                return optional.get().toDTO();
            } else {
                throw AyuException.builder().errorCode(AU_001).errorMessage(USER_CANNOT_BE_FOUND).build();
            }
        } else {
            throw AyuException.builder().errorCode(AU_001).errorMessage(USER_CANNOT_BE_FOUND).build();
        }
    }

    @Override
    public void deleteUser(int id) {
        Optional<UserEntity> optional = userRepository.findById(id);
        if(optional.isPresent()){
            userRepository.delete(optional.get());
        } else {
            throw AyuException.builder().errorCode("AYU001").errorMessage(USER_CANNOT_BE_FOUND).build();
        }
    }
}
