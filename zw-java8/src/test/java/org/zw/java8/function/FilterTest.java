package org.zw.java8.function;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import org.junit.Test;

/**
 * Created by zw on 4/19/2017.
 */
public class FilterTest {

  List<String> languages = Arrays.asList("Java", "C++", "Scala", "Haskell", "Lisp", "Javascript");

  @Test
  public void filterWithAnd() {
    Predicate<String> startWithJ = (str) -> str.startsWith("J");
    Predicate<String> fourLetterLong = (str) -> str.length() == 4;
    languages.stream().filter(startWithJ.and(fourLetterLong)).forEach(name -> {
      System.out.print(name + " ");
    });
    System.out.println();
  }

  @Test
  public void filterArray() {
    System.out.print("Languages which starts with J: ");
    filter(languages, (str) -> str.startsWith("J"));

    System.out.print("Languages which ends with a: ");
    filter(languages, (str) -> str.endsWith("a"));

    System.out.print("Print all languages: ");
    filter(languages, (str) -> true);

    System.out.print("Print no language: ");
    filter(languages, (str) -> false);

    System.out.print("Print languges whose length greater than 4");
    filter(languages, (str) -> str.length() > 4);
  }

  public void filter(List<String> languages, Predicate<String> condition) {
    languages.stream().filter((name) -> (condition.test(name))).forEach((name) -> {
      System.out.print(name + " ");
    });
    System.out.println();
  }
}
