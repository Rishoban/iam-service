package com.iam.service.controller;

import com.iam.service.entity.User;
import com.iam.service.mapper.ResponseMessage;
import com.iam.service.mapper.UserDTO;
import com.iam.service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/driving")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(final UserService userService){
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getListOfUsers(){
        List<UserDTO> result = this.userService.getListOfUsers();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("userId") Long userId){
        UserDTO user = this.userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> saveUser(@Valid @RequestBody UserDTO userPayload){
        Long recordId = this.userService.createUser(userPayload);
        ResponseMessage rsg = new ResponseMessage("Successfully inserted", 100, recordId);
        return ResponseEntity.ok(rsg);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ResponseMessage> updateUser(@Valid @RequestBody UserDTO userdata, @PathVariable("userId") Long userId){
        Long updatedUserId = this.userService.updateUser(userId, userdata);
        ResponseMessage rsg = new ResponseMessage("Successfully inserted", 101, userId);
        return ResponseEntity.ok(rsg);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<ResponseMessage> deleteUser(@PathVariable("userId") Long userId){
        this.userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

}
