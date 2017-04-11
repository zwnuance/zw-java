package org.zw.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by zw on 3/2/2017.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

  @Override public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/index").setViewName("index");
    registry.addViewController("/").setViewName("index");
    registry.addViewController("/home").setViewName("home");
    registry.addViewController("/login").setViewName("login");
  }
}
