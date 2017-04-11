package org.zw.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.Date;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by zw on 3/3/2017.
 */
public class JwtUser implements UserDetails {
  private final Long id;
  private final String username;
  private final String firstname;
  private final String lastname;
  private final String password;
  private final String email;
  private final Collection<? extends GrantedAuthority> authorities;
  private final boolean enabled;
  private final Date lastPasswordResetDate;

  public JwtUser(Long id, String username, String firstname, String lastname,
      String password, String email,
      Collection<? extends GrantedAuthority> authorities, boolean enabled,
      Date lastPasswordResetDate) {
    this.id = id;
    this.username = username;
    this.firstname = firstname;
    this.lastname = lastname;
    this.password = password;
    this.email = email;
    this.authorities = authorities;
    this.enabled = enabled;
    this.lastPasswordResetDate = lastPasswordResetDate;
  }

  @JsonIgnore
  public Long getId() {
    return id;
  }

  @Override public String getUsername() {
    return username;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  @JsonIgnore
  @Override public String getPassword() {
    return password;
  }

  public String getEmail() {
    return email;
  }

  @Override public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override public boolean isEnabled() {
    return enabled;
  }
  @JsonIgnore
  public Date getLastPasswordResetDate() {
    return lastPasswordResetDate;
  }

  @JsonIgnore
  @Override public boolean isAccountNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override public boolean isAccountNonLocked() {
    return true;
  }

  @JsonIgnore
  @Override public boolean isCredentialsNonExpired() {
    return true;
  }
}
