package cz.kul.jpa_introduction;

import cz.kul.jpa_introduction.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class JpaIntoductionApplication implements CommandLineRunner {

	@Autowired
	private DBService dbService;


	public static void main(String[] args) {
		SpringApplication.run(JpaIntoductionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		dbService.example01_nothing();
//		dbService.example02_persistEntity();
//		dbService.example03_getEntityById();
//		dbService.example04_updateData();
//		dbService.example05_updateData_doesNotWorkWhenUpdateOutOfSession();

//		dbService.prepareMoviesData();
//		dbService.example06_lazyLoading();
//		dbService.example07_JPAQueries();
		dbService.example08_joinFetch();
//		dbService.example09_sqlQueries();


	}

}
