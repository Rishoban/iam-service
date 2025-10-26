package com.iam.service.service;

import com.iam.service.entity.User;
import com.iam.service.exception.IllegalData;
import com.iam.service.exception.UserAlreadyExistsException;
import com.iam.service.exception.UserNotFoundException;
import com.iam.service.mapper.UpdateUserDto;
import com.iam.service.mapper.UserDTO;
import com.iam.service.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl extends BaseService<User, Long, UserDTO, UpdateUserDto> {

    private final UserRepository userRepo;

    public UserServiceImpl(final UserRepository userRepo){
        this.userRepo = userRepo;
    }


    @Override
    protected JpaRepository<User, Long> getRepository() {
        return this.userRepo;
    }

    @Override
    protected User mapToEntity(UserDTO createDto) {
        if(this.userRepo.existsByEmailCustom(createDto.getEmail())){
            throw new UserAlreadyExistsException("This user email is alreay exist");
        }

        return new User(createDto.getUserName(), createDto.getPassword(), createDto.getEmail());
    }

    @Override
    protected User mapToEntity(UpdateUserDto updateDto, User existingDto) {
        existingDto.setEmail(updateDto.getEmail());
        existingDto.setFirstName(updateDto.getUserName());
        existingDto.setPassword(updateDto.getPassword());
        return existingDto;
    }

    @Override
    protected RuntimeException getNotFoundException() {
        return new UserNotFoundException("This user not available");
    }
}
