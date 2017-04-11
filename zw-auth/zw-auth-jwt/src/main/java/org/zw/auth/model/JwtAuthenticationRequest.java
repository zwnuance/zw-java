package org.zw.auth.model;

import java.io.Serializable;

/**
 * Created by zw on 3/3/2017.
 */
public class JwtAuthenticationRequest implements Serializable {
  private String username;
  private String password;

  public JwtAuthenticationRequest() {
    super();
  }

  public JwtAuthenticationRequest(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
