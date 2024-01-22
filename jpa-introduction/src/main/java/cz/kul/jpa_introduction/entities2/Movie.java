package cz.kul.jpa_introduction.entities2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Movie {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private Integer creationYear;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Country country;

  @ManyToMany(fetch = FetchType.LAZY)
  private Set<Actor> actors = new HashSet<>();

  protected Movie() {
  }

  public Movie(String name, Integer creationYear, Country country) {
    this.name = name;
    this.creationYear = creationYear;
    this.country = country;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getCreationYear() {
    return creationYear;
  }

  public void setCreationYear(Integer year) {
    this.creationYear = year;
  }

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  public Set<Actor> getActors() {
    return actors;
  }

  public void setActors(Set<Actor> actors) {
    this.actors = actors;
  }

  @Override
  public String toString() {
    return "Movie{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", creationYear=" + creationYear +
        '}';
  }

}
