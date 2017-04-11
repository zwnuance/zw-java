package org.zw.auth.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.zw.auth.domain.Authority;
import org.zw.auth.model.JwtUser;
import org.zw.auth.domain.User;

/**
 * Created by zw on 3/3/2017.
 */
public final class JwtUserFactory {
  private JwtUserFactory() {

  }

  public static JwtUser create(User user) {
    return  new JwtUser(
        user.getId(),
        user.getUsername(),
        user.getFirstname(),
        user.getLastname(),
        user.getPassword(),
        user.getEmail(),
        manyToGrantedAuthorites(user.getAuthorities()),
        user.getEnabled(),
        user.getLastPasswordResetDate()
        );
  }

  private static List<GrantedAuthority> manyToGrantedAuthorites(List<Authority> authorities) {
    return authorities.stream()
            .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
            .collect(Collectors.toList());
  }
}
