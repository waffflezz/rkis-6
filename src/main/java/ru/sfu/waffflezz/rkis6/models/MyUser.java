package ru.sfu.waffflezz.rkis6.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class MyUser {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int id;

  @Column(name = "username")
  @NotEmpty
  @Size(min = 1, max = 100)
  private String username;

  @NotEmpty
  @Column(name = "password")
  private String password;

  @Column(name = "role")
  private String role;
}
