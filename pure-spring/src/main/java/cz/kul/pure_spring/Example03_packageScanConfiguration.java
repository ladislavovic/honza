package cz.kul.pure_spring;

import cz.kul.pure_spring.package1.Service1;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

public class Example03_packageScanConfiguration {

  @Configuration
  @ComponentScan("cz.kul.pure_spring.package1")
  public static class ComponentScanConfiguration {
  }

  public static void main(String[] args) {
    try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext()) {
      ctx.register(ComponentScan.class);
      ctx.refresh();

      Service1 bean = ctx.getBean(Service1.class);
    }
  }
}
