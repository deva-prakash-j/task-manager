package com.task.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Tasks")
public class TaskController {

  @GetMapping
  public String getAllTasks() {
    return "hi";
  }
}
