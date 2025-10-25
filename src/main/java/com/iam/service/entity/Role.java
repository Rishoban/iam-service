package com.iam.service.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="tbl_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="role_name")
    private String roleName;

    @ManyToMany(
            mappedBy = "roleList",
            fetch = FetchType.LAZY
    )
    private List<Group> groupList;

    public Role(Long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
