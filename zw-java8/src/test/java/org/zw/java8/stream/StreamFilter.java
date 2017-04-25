package org.zw.java8.stream;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
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
    String contents = new String(Files.readAllBytes(Paths.get(getClass().getResource("/alice.txt").getFile().substring(1))), Charsets.UTF_8);
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
    Stream<String> lines = Files.lines(Paths.get(getClass().getResource("/alice.txt").getFile().substring(1)));
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
