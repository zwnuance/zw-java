package org.zw.java8.lambda;

import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;
import org.zw.java8.entity.Country;

/**
 * Created by zw on 4/20/2017.
 */
public class ConvertCollectionTest {

  List<Country> G7 = new ArrayList<>();

  @Before
  public void init() {
    G7.add(new Country("US", "USA"));
    G7.add(new Country("JP", "Japan"));
    G7.add(new Country("FR", "France"));
    G7.add(new Country("DE", "Germany"));
    G7.add(new Country("IT", "Italy"));
    G7.add(new Country("UK", "U.K"));
    G7.add(new Country("CA", "Canada"));
  }

  @Test
  public void createMap() {
    Map<String, Country> G7Map = new HashMap<>();
    for (Country country : G7) {
      G7Map.put(country.getCode(), country);
    }
    System.out.println(G7Map);
  }

  @Test
  public void createMapInGuava() {
    Map<String, Country> G7Map =
        Maps.uniqueIndex(G7, new com.google.common.base.Function<Country, String>() {
          @Override public String apply(Country country) {
            return country.getCode();
          }
        });
    System.out.println(G7Map);
  }

  @Test
  public void createMapInGuavaLambda() {
    Map<String, Country> G7Map = Maps.uniqueIndex(G7, country -> country.getCode());
    System.out.println(G7Map);
  }

  @Test
  public void createMapInLambda() {
    Map<String, Country> G7Map =
        G7.stream().collect(Collectors.toMap(Country::getCode, Function.identity()));
    System.out.println(G7Map);
  }
}

