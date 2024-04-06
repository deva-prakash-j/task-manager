package com.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.task.common.ResourceAlreadyExistException;
import com.task.dto.UserDTO;
import com.task.entity.UserEntity;
import com.task.mappers.UserMapper;
import com.task.repo.UserRepo;

@Service
public class UserService {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserRepo repo;

  public UserEntity registerUser(UserDTO user) {
    try {
      String hashPwd = passwordEncoder.encode(user.getPwd());
      user.setPwd(hashPwd);
      UserEntity userEntity = UserMapper.dtoToEntity(user);
      return repo.save(userEntity);
    } catch (DataIntegrityViolationException e) {
      throw new ResourceAlreadyExistException("User with given emailId already Exist!");
    }
  }
}
