package cz.kul.pure_spring.package3;

import org.springframework.beans.factory.annotation.Autowired;

public class FileCompressor {

  @Autowired
  private CompressionAlgorithm compressionAlgorithm;

  public CompressionAlgorithm getCompressionAlgorithm() {
    return compressionAlgorithm;
  }

}
