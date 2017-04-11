package org.zw.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zw.auth.domain.User;

/**
 * Created by zw on 3/3/2017.
 */
public interface UserRepository extends JpaRepository<User, Long> {
  User findUserByUsername(String username);
}
