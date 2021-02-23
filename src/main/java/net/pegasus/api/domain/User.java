package net.pegasus.api.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "app_users")
@Data
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String username;

  private String email;

  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  private Set<Role> roles;

}
