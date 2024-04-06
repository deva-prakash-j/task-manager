package com.task.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.task.common.Constants;
import com.task.dto.ResponseDTO;
import com.task.dto.UserDTO;
import com.task.entity.UserEntity;
import com.task.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@Tag(name = "Users")
@Validated
public class UserController {

  @Autowired
  UserService service;

  @PostMapping("/register")
  public ResponseEntity<ResponseDTO> register(@Valid @RequestBody UserDTO user) {
    UserEntity savedUser = this.service.registerUser(user);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseDTO(Constants.SUCCESS_STATUS, null, null, List.of(savedUser)));
  }
}
