package com.task.mappers;

import com.task.dto.UserDTO;
import com.task.entity.UserEntity;

public class UserMapper {

  public static UserEntity dtoToEntity(UserDTO dto) {
    UserEntity entity = new UserEntity();
    entity.setName(dto.getName());
    entity.setEmail(dto.getEmail());
    entity.setPwd(dto.getPwd());
    return entity;
  }
}
