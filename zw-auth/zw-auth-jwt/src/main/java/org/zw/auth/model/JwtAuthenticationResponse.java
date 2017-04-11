package org.zw.auth.model;

import java.io.Serializable;

/**
 * Created by zw on 3/3/2017.
 */
public class JwtAuthenticationResponse implements Serializable {

  private final String token;

  public JwtAuthenticationResponse(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }
}
