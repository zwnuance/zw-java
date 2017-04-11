package org.zw.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zw.auth.repository.UserRepository;

import org.zw.auth.domain.User;

/**
 * Created by zw on 3/3/2017.
 */
@Service
public class JwtUserDetailServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findUserByUsername(username);
    if(user == null) {
      throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
    }else {
      return JwtUserFactory.create(user);
    }
  }
}
