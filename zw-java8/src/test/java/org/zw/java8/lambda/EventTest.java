package org.zw.java8.lambda;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.junit.Test;

/**
 * Created by zw on 4/19/2017.
 */
public class EventTest {

  @Test
  public void noLambda() {
    new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent event) {
        System.out.println("source [" + event.getSource() + "]" + "Thanks for clicking!");
      }
    };
  }

  @Test
  public void useLambda() {
    EventHandler<ActionEvent> listener = event -> {
      System.out.println("source [" + event.getSource() + "]" + "Thanks for clicking!");
    };
  }
}
