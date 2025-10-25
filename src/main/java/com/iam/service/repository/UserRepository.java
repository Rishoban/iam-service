package com.iam.service.repository;

import com.iam.service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(
            value = "SELECT EXISTS(SELECT 1 FROM users WHERE email = ?1)",
            nativeQuery = true
    )
    Boolean existsByEmailCustom(String email);
}
