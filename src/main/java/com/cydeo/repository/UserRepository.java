package com.cydeo.repository;

import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByIsDeleted(Boolean isDeleted);

    List<User> findAllByRoleDescriptionIgnoreCaseAndIsDeleted(String role, Boolean isDeleted);

    User findByUserNameAndIsDeleted(String username, Boolean isDeleted);

    User findByIdAndIsDeleted(Long id, Boolean isDeleted);
}
