package org.zw.java8.stream;

import com.google.common.base.Charsets;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zw on 4/25/2017.
 */
public class StreamFilter {

  private List<String> words;

  @Before
  public void init() throws IOException {
    String contents = new String(Files.readAllBytes(Paths.get("D:\\TMP\\alice.txt")), Charsets.UTF_8);
    words = Arrays.asList(contents.split("[\\P{L}]"));
  }

  @Test
  public void noLambda() throws IOException {
    int count = 0;
    for (String word : words) {
      if(word.length() > 12) {
        count ++;
      }
    }
    assertEquals(16, count);
  }

  @Test
  public void useLambda() {
    long count = words.stream().filter(w -> w.length() > 12).count();
    assertEquals(16, count);
  }

  @Test
  public void useLambdaParallel() {
    long count = words.parallelStream().filter(w -> w.length() > 12).count();
    assertEquals(16, count);
  }

  @Test
  public void testSubStream() throws IOException {
    Stream<String> lines = Files.lines(Paths.get("D:\\TMP\\alice.txt"));
    long count =
        lines.map(w -> Arrays.asList(w.split("[\\P{L}]")).stream().filter(c -> c.length() > 12).count())
            .reduce((sum, c) -> sum + c).get();
    assertEquals(16, count);
  }

  @Test
  public void testConvertCase() {
    //List<String> collect = words.stream().map(String::toUpperCase).collect(Collectors.toList());
    words.stream().filter(w -> w.length() > 0).map(String::toUpperCase);
  }
}
