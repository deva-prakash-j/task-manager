package com.task.entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class UserEntity {

  @Id
  @SequenceGenerator(name = "id_generator", initialValue = 10001, allocationSize = 1)
  @GeneratedValue(generator = "id_generator")
  @Column(name = "id")
  @Schema(accessMode = AccessMode.READ_ONLY)
  private Integer id;

  private String name;

  @Column(unique = true, nullable = false)
  private String email;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String pwd;

  @Schema(accessMode = AccessMode.READ_ONLY)
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Schema(accessMode = AccessMode.READ_ONLY)
  @UpdateTimestamp
  private LocalDateTime updatedAt;

}
