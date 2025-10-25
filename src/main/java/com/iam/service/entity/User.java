package com.iam.service.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="firstname")
    private String firstName;

    @Column(name="password")
    private String password;

    @Column(name="email", unique = true)
    private String email;

    @CreationTimestamp
    @Column(name="created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @ManyToMany(
            mappedBy = "userList",
            fetch = FetchType.LAZY
    )
    private List<Group> groupList;

    public User( String firstName, String password, String email) {
        this.id = id;
        this.firstName = firstName;
        this.password = password;
        this.email = email;
        this.createdAt = LocalDateTime.now();
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
