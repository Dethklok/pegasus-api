package net.pegasus.api.domain;

import lombok.Data;

import javax.persistence.*;

@Data
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String username;

  private String email;

  private String password;

}
