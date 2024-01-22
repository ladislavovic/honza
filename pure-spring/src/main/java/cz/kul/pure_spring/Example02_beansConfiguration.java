package cz.kul.pure_spring;

import cz.kul.pure_spring.package3.CompressionAlgorithm;
import cz.kul.pure_spring.package3.FileCompressor;
import cz.kul.pure_spring.package3.ZipAlgorithm;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class Example02_beansConfiguration {

  @Configuration
  public static class BeansConfiguration {

    @Bean
    ZipAlgorithm zipAlgorithm() {
      return new ZipAlgorithm();
    }

    @Bean
    FileCompressor fileCompressor() {
      return new FileCompressor();
    }

  }

  public static void main(String[] args) {
    try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext()) {
      ctx.register(BeansConfiguration.class);
      ctx.refresh();

      FileCompressor fc1 = ctx.getBean("fileCompression", FileCompressor.class);
      FileCompressor fc2 = ctx.getBean(FileCompressor.class);
      if (fc1 != fc2) {
        throw new RuntimeException("They should be identical.");
      }

      CompressionAlgorithm compressionAlgorithm = fc1.getCompressionAlgorithm();
      if (!(compressionAlgorithm instanceof ZipAlgorithm)) {
        throw new RuntimeException("It should be zip algorithm");
      }
    }
  }

}
