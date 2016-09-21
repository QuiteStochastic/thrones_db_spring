package thrones_db_spring;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.PostConstruct;

/**
 * Created by oliverlee
 */
@SpringBootApplication
@EnableAsync
public class Application {


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}



/*	@Value("${schema}")
	private String schemaName;

	@Value("${hibernate.connection.url}")
	private String db_url;

	@Value("${hibernate.connection.username}")
	private String db_UN;

	@Value("${hibernate.connection.password}")
	private String db_PW;


	@PostConstruct
	public void runFlywayScripts() {

		System.out.println("INITIALIZING db");
		System.out.println("db_fullURL: "+db_url);
		System.out.println("db_User: "+db_UN);
		System.out.println("db_PW: "+db_PW);
		System.out.println("scheme: "+schemaName);
		System.out.println("setting up flyway");

		Flyway flyway = new Flyway();
		flyway.setDataSource(db_url, db_UN, db_PW);
		flyway.setSchemas(schemaName);

		System.out.println("executing flyway migrate\n");
		flyway.migrate();

	}*/

}
