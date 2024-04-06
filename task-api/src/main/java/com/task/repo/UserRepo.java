package com.task.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.task.entity.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Integer> {

}
