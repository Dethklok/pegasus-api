package net.pegasus.api.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table
public enum Role implements GrantedAuthority {

  USER, ADMIN;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;

  @Override
  public String getAuthority() {
    return name;
  }

}
