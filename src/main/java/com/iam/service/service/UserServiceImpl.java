package com.iam.service.service;

import com.iam.service.entity.User;
import com.iam.service.exception.IllegalData;
import com.iam.service.exception.UserAlreadyExistsException;
import com.iam.service.exception.UserNotFoundException;
import com.iam.service.mapper.UserDTO;
import com.iam.service.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    public UserServiceImpl(final UserRepository userRepo){
        this.userRepo = userRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getListOfUsers() {
        try{
            List<User> listOfUsers = this.userRepo.findAll();

            return listOfUsers.stream()
                    .map(user -> new UserDTO(user.getFirstName(), user.getPassword(), user.getEmail()))
                    .toList();

        }catch(Exception e){
            throw new RuntimeException("This is error in getting users " + e.getMessage());
        }
        
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserById(Long id) {
        try{
            Optional<User> userDataOpt = this.userRepo.findById(id);

            if (userDataOpt.isPresent()){
                User userData = userDataOpt.get();
                return new UserDTO(userData.getFirstName(), userData.getPassword(), userData.getEmail());
            }else{
                throw new UserNotFoundException("Requested user not found");
            }
        }catch (Exception e){
            throw new UserNotFoundException("Requested user not found");
        }

    }


//    @Transactional
//    public Long saveUserData(User userData) {
//        try{
//            User savedUser = this.userRepo.save(userData);
//            return savedUser.getId();
//        }catch (IllegalData e){
//            throw new IllegalData("Invalid data");
//        }
//    }
    @Override
    @Transactional
    public Long createUser(UserDTO userDTO) {
        try {
            // Business validation
            validateUserCreation(userDTO);

            // Create User entity
            User userData = new User();
            userData.setFirstName(userDTO.getUserName()); // Adjust field mapping as needed
            userData.setPassword(userDTO.getPassword()); // In production, hash the password!
            userData.setEmail(userDTO.getEmail());

            // Save user
            User savedUser = this.userRepo.save(userData);


            return savedUser.getId();

        } catch (DataIntegrityViolationException e) {

            throw new UserAlreadyExistsException("User with provided details already exists");
        } catch (Exception e) {
            throw new RuntimeException("Failed to create user", e);
        }
    }

    @Override
    public Long updateUser(Long userId, UserDTO userDTO) {
        try {
            // Business validation
            validateUserCreation(userDTO);

            this.userRepo.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("There is no user for this id"));

            // Create User entity
            User userData = new User();
            userData.setFirstName(userDTO.getUserName()); // Adjust field mapping as needed
            userData.setPassword(userDTO.getPassword()); // In production, hash the password!
            userData.setEmail(userDTO.getEmail());

            // Save user
            User savedUser = this.userRepo.save(userData);
            return savedUser.getId();

        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyExistsException("User with provided details already exists");
        } catch (Exception e) {
            throw new RuntimeException("Failed to create user", e);
        }
    }

    @Override
    public void deleteUserById(Long id) {
        try{
            this.userRepo.findById(id)
                    .orElseThrow(() -> new UserNotFoundException("There is no user for this id"));
            this.userRepo.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("Failed to perform delete operation");
        }

    }

    private void validateUserCreation(UserDTO userDTO) {
        // Check if user already exists by email
        if (userRepo.existsByEmailCustom(userDTO.getEmail())) {
            throw new UserAlreadyExistsException("User already exists with email: " + userDTO.getEmail());
        }

        // Check if username already exists (if you have this field)
        // if (userRepo.existsByUserName(userDTO.getUserName())) {
        //     throw new UserAlreadyExistsException("User already exists with username: " + userDTO.getUserName());
        // }
    }
}
