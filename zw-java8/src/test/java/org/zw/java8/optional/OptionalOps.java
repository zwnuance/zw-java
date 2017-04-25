package org.zw.java8.optional;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zw on 4/25/2017.
 */
public class OptionalOps {

  Stream<String> words;

  @Before
  public void init() throws IOException {
    String contents = new String(
        Files.readAllBytes(Paths.get(getClass().getResource("/alice.txt").getFile().substring(1))), StandardCharsets.UTF_8);
    words = Stream.of(contents.split("[\\P{L}]"));
  }

  @Test
  public void testMax() {
    Optional<String> largest = words.max(String::compareToIgnoreCase);
    assertTrue(largest.isPresent());
  }

  @Test
  public void startWithFirst() {
    Optional<String> startWith = words.parallel().filter(w -> w.startsWith("Q")).findFirst();
    assertNotNull(startWith.get());
  }

  @Test
  public void startWithAny() {
    Optional<String> startWith = words.parallel().filter(w -> w.startsWith("Q")).findAny();
    assertNotNull(startWith.get());
  }

  @Test
  public void anyMatch() {
    boolean match = words.parallel().anyMatch(w -> w.startsWith("Q"));
    assertTrue(match);
  }

  @Test
  public void optionalCreate() {
    Optional<String> emptyOptional = Optional.empty();
    Optional<String> ofOptional = Optional.of("Hello");
    String s = emptyOptional.orElse("not empty");
    String s1 = ofOptional.orElse("not empty");
    assertEquals("not empty", s);
    assertEquals("Hello", s1);
  }
}
