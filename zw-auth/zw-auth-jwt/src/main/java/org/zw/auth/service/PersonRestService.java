package org.zw.auth.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.zw.auth.model.Person;

/**
 * Created by zw on 3/3/2017.
 */
public class PersonRestService {
  private static final List<Person> persons;

  static {
    persons = new ArrayList<>();
    persons.add(new Person("hello", "world"));
    persons.add(new Person("foo", "bar"));
  }

  @GetMapping("/persons")
  public static List<Person> getPersons() {
    return persons;
  }

  @GetMapping("/persons/{name}")
  public static Person getPerson(@PathVariable("name") String name) {
    return persons.stream()
            .filter(person -> name.equalsIgnoreCase(person.getName()))
            .findAny().orElse(null);
  }
}
