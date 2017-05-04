package org.zw.java8.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import jdk.nashorn.internal.runtime.options.Option;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zw on 4/27/2017.
 */
public class StreamTest {

  private List<String> warAndSpace;

  private List<String> alice;

  @Before
  public void init() throws IOException {
    String contents = new String(Files.readAllBytes(Paths.get(getClass().getResource("/WarAndPeace.txt").getFile().substring(1))), StandardCharsets.UTF_8);
    warAndSpace = Arrays.asList(contents.split("[\\P{L}]"));

    contents = new String(Files.readAllBytes(Paths.get(getClass().getResource("/alice.txt").getFile().substring(1))), StandardCharsets.UTF_8);
    alice = Arrays.asList(contents.split("[\\P{L}]"));
  }

  @Test
  public void parallelFor() {
    int cpus = Runtime.getRuntime().availableProcessors();
    ExecutorService threadPool = Executors.newFixedThreadPool(cpus);
  }

  @Test
  public void benchmark() throws IOException {
    long b = System.nanoTime();
    Instant begin = Instant.now();
    Long count1 = warAndSpace.stream().parallel()
        .map(w -> Arrays.stream(w.split("[\\P{L}]")).map(c -> c.length() > 12).count())
        .reduce((sum, l) -> sum + l)
        .get();
    Duration duration1 = Duration.between(begin, Instant.now());
    System.out.println(System.nanoTime() - b);

    b = System.nanoTime();
    begin = Instant.now();
    Long count2 = warAndSpace.parallelStream()
        .map(w -> Arrays.stream(w.split("[\\P{L}]")).map(c -> c.length() > 12).count())
        .reduce((sum, l) -> sum + l)
        .get();

    System.out.println(System.nanoTime() - b);

    Duration duration2 = Duration.between(begin, Instant.now());
    assertEquals(count1, count2);
    System.out.println(duration1.getNano());
    System.out.println(duration2.getNano());
  }

  @Test
  public void testCollectionRemove() {
    List<String> months =
        Arrays.stream(Month.values()).map(Month::toString).map(String::toLowerCase).collect(
            Collectors.toList());
    months.removeIf(w -> w.startsWith("j"));
    months.stream().forEach(System.out::println);
  }
}
