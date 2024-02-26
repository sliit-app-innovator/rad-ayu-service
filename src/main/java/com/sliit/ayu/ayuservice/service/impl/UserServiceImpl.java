package com.sliit.ayu.ayuservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sliit.ayu.ayuservice.dto.UserDTO;
import com.sliit.ayu.ayuservice.model.UserEntity;
import com.sliit.ayu.ayuservice.repository.UserRepository;
import com.sliit.ayu.ayuservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class UserServiceImpl implements UserService  {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper ObjectMapper;

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
        return null;
    }

    @Override
    public UserDTO getUser(int id) {
        return null;
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public void deleteUser(int id) {

    }
}
