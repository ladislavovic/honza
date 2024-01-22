package cz.kul.pure_spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

public class Example01_emptyConfiguration {

  @Configuration
  public static class EmptyConfiguration {
  }

  public static void main(String[] args) {
    try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext()) {
      ctx.register(EmptyConfiguration.class);
      ctx.refresh();

      // Ends with error because there is no such bean
      //ctx.getBean("foo");
    }
  }

}
