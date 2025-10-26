package com.iam.service.controller;

import com.iam.service.entity.User;
import com.iam.service.mapper.UpdateUserDto;
import com.iam.service.mapper.UserDTO;
import com.iam.service.service.BaseService;
import com.iam.service.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/driving")
public class UserController extends BaseController<User, Long, UserDTO, UpdateUserDto> {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(final UserServiceImpl userService){
        this.userService = userService;
    }


    @Override
    protected BaseService<User, Long, UserDTO, UpdateUserDto> getService() {
        return this.userService;
    }
}
