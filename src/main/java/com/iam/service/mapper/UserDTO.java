package com.iam.service.mapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    
    @NotBlank(message = "Username is required")
    private String userName;
    
    @NotBlank(message = "Password is required")
    private String password;

    @NotNull(message = "Email is required")
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;
    
    // Default constructor
    public UserDTO() {
    }
    
    // Constructor with all fields
    public UserDTO(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
    
    // Getters and Setters
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
}
