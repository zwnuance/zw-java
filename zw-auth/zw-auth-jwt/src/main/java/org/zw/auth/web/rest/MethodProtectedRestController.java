package org.zw.auth.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zw on 3/3/2017.
 */
@RestController
public class MethodProtectedRestController {

  @GetMapping("/protected")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> getProtectedGreeting() {
    return ResponseEntity.ok("Greetings from admin protected method!");
  }
}
