package org.zw.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by zw on 3/2/2017.
 */
public class CurrentUserDetailsService implements UserDetailsService {
  @Override public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    return null;
  }
}
