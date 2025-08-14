package com.example.benchmark.repo;

import com.example.benchmark.domain.User;
import com.example.benchmark.dto.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT id AS id, first_name AS firstName, last_name AS lastName FROM users", nativeQuery = true)
    List<UserProjection> findAllUsersProjection();
}
