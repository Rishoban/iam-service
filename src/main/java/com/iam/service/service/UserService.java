package com.iam.service.service;

import com.iam.service.entity.User;
import com.iam.service.mapper.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getListOfUsers();
    UserDTO getUserById(Long id);
    Long createUser(UserDTO userDTO);
    Long updateUser(Long userId,UserDTO userDTO);
    void deleteUserById(Long id);
}
