package com.example.todolist.repository;

import com.example.todolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(nativeQuery = true, value = "SELECT user_id FROM user s WHERE s.username = :username")
    int findUserIdByUsername(@Param("username") String username);
}
