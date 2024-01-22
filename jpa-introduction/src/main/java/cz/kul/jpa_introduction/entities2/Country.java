package cz.kul.jpa_introduction.entities2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Country {

  @Id
  @Column(length = 4)
  private String code;

  @Column(nullable = false)
  private String name;

  protected Country() {
  }

  public Country(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public String getName() {
    return name;
  }

}
