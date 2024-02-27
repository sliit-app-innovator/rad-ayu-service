package com.sliit.ayu.ayuservice.service;

import com.sliit.ayu.ayuservice.dto.UserDTO;
import com.sliit.ayu.ayuservice.dto.UserUpdateDTO;

import java.util.List;

public interface UserService {
    public UserDTO addUser(UserDTO userDTO);
    public List<UserDTO> searchUser(String name);
    public UserDTO getUser(int id);
    public UserDTO updateUser(UserUpdateDTO userDTO);
    public void deleteUser(int id);
}
