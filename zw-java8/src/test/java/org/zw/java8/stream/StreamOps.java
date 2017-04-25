package org.zw.java8.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zw on 4/25/2017.
 */
public class StreamOps {

  Stream<Double> stream;

  List<String> words;

  @Before
  public void init() throws IOException {
     stream = Stream.generate(Math::random);
    String contents = new String(
        Files.readAllBytes(Paths.get(getClass().getResource("/alice.txt").getFile().substring(1))),
        StandardCharsets.UTF_8);
    words = Arrays.asList(contents.split("[\\P{L}]"));
  }

  public void testLimit() {
    Stream<Double> limitStream = stream.limit(100);
  }

  public void testSkip() {
    Stream<Double> skipStream = stream.skip(100);
  }

  @Test
  public void testConcat() {
    Stream<String> concatStream = Stream.concat(Stream.of("Hello"), Stream.of("World"));
    String collect = concatStream.collect(Collectors.joining());
    assertEquals("HelloWorld", collect);
  }

  @Test
  public void testIterate() {
    Object[] objects = Stream.iterate(1, p -> p * 2)
        .peek(e -> System.out.println("Fetching " + e))
        .limit(20)
        .toArray();
    assertEquals(20, objects.length);
  }

  @Test
  public void testCompare() {
    Stream<String> longestFirst = words.stream().filter(w -> w.length() > 0).sorted(
        Comparator.comparing(String::length).reversed());
    longestFirst.forEach(System.out::println);
  }
}
