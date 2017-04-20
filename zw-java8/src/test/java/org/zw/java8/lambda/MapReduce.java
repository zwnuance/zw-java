package org.zw.java8.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;

/**
 * Created by zw on 4/20/2017.
 */
public class MapReduce {

  List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
  
  @Test
  public void mapNoLambda() {
    for (Integer cost : costBeforeTax) {
      double price = cost + 0.12 * cost;
      System.out.println(price);
    }
  }

  @Test
  public void mapUseLambda() {
    costBeforeTax.stream().map((cost) -> (cost + cost * 0.12)).forEach(System.out::println);
  }

  @Test
  public void reduceNoLambda() {
    double total = 0;
    for (Integer cost : costBeforeTax) {
      double price = cost + cost * 0.12;
      total += price;
    }
    System.out.println("Total " + total);
  }

  @Test
  public void reduceUseLambda() {
    Double total = costBeforeTax.stream()
        .map((cost) -> (cost + cost * 0.12))
        .reduce((sum, cost) -> sum + cost)
        .get();
    System.out.println("Total " + total);
  }

  @Test
  public void collectNoLambda() {
    List<Integer> filtered = new ArrayList<>();
    for (Integer cost : costBeforeTax) {
      if(cost > 200) {
        filtered.add(cost);
      }
    }
    System.out.println(filtered);
  }

  @Test
  public void collectUseLambda() {
    List<Integer> filtered =
        costBeforeTax.stream().filter((cost) -> (cost > 200)).collect(Collectors.toList());
    System.out.println(filtered);
  }

  @Test
  public void lambdaElementFunction() {
    List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K", "Canada");
    String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(","));
    System.out.println(G7Countries);
  }

  @Test
  public void lambdaStatistics() {
    List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
    IntSummaryStatistics statistics =
        primes.stream().mapToInt((x) -> x).summaryStatistics();
    System.out.println("Highest prime number in list is: " + statistics.getMax());
    System.out.println("Lowest primer number in list is: " + statistics.getMin());
    System.out.println("Sum of all prime numbers: " + statistics.getCount());
    System.out.println("Average of all prime numbers: " + statistics.getAverage());
  }
}
