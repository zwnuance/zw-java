package org.zw.java8.utils;

/**
 * Created by zw on 4/20/2017.
 */
public class Country {

  public Country() {
  }

  public Country(String code, String name) {
    this.code = code;
    this.name = name;
  }

  private String code;

  private String name;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override public String toString() {
    return "Country{" +
        "code='" + code + '\'' +
        ", name='" + name + '\'' +
        '}';
  }
}
