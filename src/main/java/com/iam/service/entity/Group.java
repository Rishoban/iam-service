package com.iam.service.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="tbl_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="group_name")
    private String groupName;

    @ManyToMany(
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name="group_user_map",
            joinColumns = @JoinColumn(
                    name="group_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name="user_id",
                    referencedColumnName = "id"
            )
    )
    private List<User> userList;

    @ManyToMany(
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name="group_role_map",
            joinColumns = @JoinColumn(
                    name="group_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name="roel_id",
                    referencedColumnName = "id"
            )
    )
    private List<Role> roleList;

    public Group(Long id, String groupName) {
        this.id = id;
        this.groupName = groupName;
    }

    public Group() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
