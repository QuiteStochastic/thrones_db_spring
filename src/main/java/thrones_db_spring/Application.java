package thrones_db_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Created by oliverlee
 */
@SpringBootApplication
@EnableAsync
public class Application {


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

}
