package cz.kul.jpa_introduction;

import cz.kul.jpa_introduction.entities.Customer;
import cz.kul.jpa_introduction.entities2.Actor;
import cz.kul.jpa_introduction.entities2.Country;
import cz.kul.jpa_introduction.entities2.Movie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Service
public class DBService {

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  private PlatformTransactionManager transactionManager;

  @Autowired
  private ApplicationContext applicationContext;


  public void example01_nothing() {
  }

  @Transactional
  public void example02_persistEntity() {                             // CREATE
    Customer customer = new Customer("Pepa", "Novak");
    entityManager.persist(customer);
  }

  public void example03_getEntityById() {                               // READ
    Customer customer = entityManager.find(Customer.class, 1);
    System.out.println("The customer: " + customer);
  }

  @Transactional
  public void example04_updateData() {                                 // UPDATE
    Customer customer = entityManager.find(Customer.class, 1);
    customer.setFirstName("Karel");
    // NOTE: you does not have to save data manually, Hibernate detect changes
    // and store changes automatically
  }

  public void example05_updateData_doesNotWorkWhenUpdateOutOfSession() {
    Customer customer = getCustomer(1L);
    customer.setFirstName("Martin");
    // NOTE: here it does not work because it is out of opened session

    DBService dbService = applicationContext.getBean(DBService.class);
    dbService.updateCustomer(customer);
  }

  @Transactional
  private Customer getCustomer(Long id) {
    return entityManager.find(Customer.class, id);
  }

  @Transactional
  public void updateCustomer(Customer customer) {
    entityManager.merge(customer);
  }

  @Transactional
  public void exampleX_delete() {
    Customer customer = entityManager.find(Customer.class, 1);
    entityManager.remove(customer);
  }

  @Transactional
  public void prepareMoviesData() {
    Country usa = new Country("US", "United States of America");
    Country cz = new Country("CZ", "Czech Republic");
    entityManager.persist(usa);
    entityManager.persist(cz);

    Actor pepa = new Actor("Pepa", "Novak", Actor.Sex.MALE);
    Actor misa = new Actor("Misa", "Novakova", Actor.Sex.FEMALE);
    Actor karel = new Actor("Karel", "Zavodny", Actor.Sex.FEMALE);
    entityManager.persist(pepa);
    entityManager.persist(misa);
    entityManager.persist(karel);

    Movie lotr1 = new Movie("LOTR 1", 2000, usa);
    lotr1.getActors().add(pepa);
    lotr1.getActors().add(misa);
    entityManager.persist(lotr1);

    Movie lotr2 = new Movie("LOTR 2", 2002, usa);
    lotr2.getActors().add(karel);
    entityManager.persist(lotr2);
  }

  public void example06_lazyLoading() {
    TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);

    Movie movie = transactionTemplate.execute(status -> {
          // ZACATEK TX
          Movie m = entityManager.find(Movie.class, 1L);
          return m;
          // KONEC TX
        }
    );

    // NOTE: the variable "country" contains lazy proxy, not the country object
    Country country = movie.getCountry();
    System.out.println("Country class: " + country.getClass().getName());

    // NOTE: when you try to get anything else than id from the lazy proxy, it
    // ends in error
    country.getName(); // <-- this cause LazyInitializationException
  }

  public void example07_JPAQueries() {
    String movieName = "LOTR 2";
    String queryString = """
        select m
        from Movie m
        where m.name like '%LOTR%'
        """;
    TypedQuery<Movie> query = entityManager.createQuery(queryString, Movie.class);
//    query.setParameter("name", movieName);
    List<Movie> resultList = query.getResultList();
    System.out.println("Results: " + resultList);
  }

  public void example08_joinFetch() {
    List<Movie> allMovies = getAllMoviesWithActorsAndCountries();
    Movie first = allMovies.getFirst();

    // NOTE: this does not cause LazyInitializationException, because country
    // data are already loaded
    System.out.printf(
        "Movie name: %s, Movie country: %s%n",
        first.getName(),
        first.getCountry().getName());
  }

  private List<Movie> getAllMoviesWithActorsAndCountries() {
    String queryString = """
        select m
        from Movie m
        left join fetch m.actors
        left join fetch m.country
        """;
    TypedQuery<Movie> query = entityManager.createQuery(queryString, Movie.class);
    return query.getResultList();
  }

  public void example09_sqlQueries() {
    // Scalar queries
    System.out.println("Scalar query:");
    Query nativeQuery = entityManager.createNativeQuery("SELECT m.id, m.name FROM movie as m");
    List<Object[]> rows = nativeQuery.getResultList();
    for (Object[] row : rows) {
      System.out.printf("id: %s, name: %s\n", row[0], row[1]);
    }

    System.out.println("\nEntityQuery:");
    Query nativeQuery1 = entityManager.createNativeQuery("SELECT * from movie m", Movie.class);
    List<Movie> movies = nativeQuery1.getResultList();
    for (Movie movie : movies) {
      System.out.println(movie);
    }
  }


}
