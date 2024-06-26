package com.sliit.ayu.ayuservice.service.impl;

import com.sliit.ayu.ayuservice.constants.ErrorCode;
import com.sliit.ayu.ayuservice.dto.UserDTO;
import com.sliit.ayu.ayuservice.dto.UserUpdateDTO;
import com.sliit.ayu.ayuservice.execption.AyuException;
import com.sliit.ayu.ayuservice.model.UserEntity;
import com.sliit.ayu.ayuservice.repository.UserRepository;
import com.sliit.ayu.ayuservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService  {

    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailServiceImpl emailService;

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        userDTO.setCreatedDate(Calendar.getInstance().getTime());
        userDTO.setUpdatedDate(Calendar.getInstance().getTime());
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(userDTO.toEntity());
        UserEntity userEntity = userRepository.findByEmployeeNumber(userDTO.getEmployeeNumber());

        emailService.sendNewUserCreation(userEntity.toDTO());
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
           throw AyuException.builder().errorCode(ErrorCode.AU_001.getCode()).errorMessage(ErrorCode.AU_001.getMessage()).build();
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
            if (userDTO.getDepartmentId() != null && userDTO.getDepartmentId()!=0) {
                userEntity.setDepartmentId(userDTO.getDepartmentId());
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
                throw AyuException.builder().errorCode(ErrorCode.AU_001.getCode()).errorMessage(ErrorCode.AU_001.getMessage()).build();
            }
        } else {
            throw AyuException.builder().errorCode(ErrorCode.AU_001.getCode()).errorMessage(ErrorCode.AU_001.getMessage()).build();
        }
    }

    @Override
    public void deleteUser(int id) {
        Optional<UserEntity> optional = userRepository.findById(id);
        if(optional.isPresent()){
            userRepository.delete(optional.get());
        } else {
            throw AyuException.builder().errorCode(ErrorCode.AU_001.getCode()).errorMessage(ErrorCode.AU_001.getMessage()).build();
        }
    }
}
