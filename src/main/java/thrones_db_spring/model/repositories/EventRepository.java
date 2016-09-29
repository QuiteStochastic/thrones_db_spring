package thrones_db_spring.model.repositories;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Repository;
import thrones_db_spring.model.Episode;
import thrones_db_spring.model.Event;
import thrones_db_spring.model.Location;

import javax.annotation.PostConstruct;
import java.util.Properties;

/**
 * Created by oliverlee
 */
@Repository
public class EventRepository extends AbstractRepository{

	private SessionFactory factory;


	@PostConstruct
	public void initFactory(){

		Properties prop= super.initProperties();

		try {
			ServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder()
					.applySettings( prop )
					.build();
			factory = new Configuration()
					.addPackage("thrones_db_spring.model")
					.addProperties(prop)
					.addAnnotatedClass(Event.class)
					.addAnnotatedClass(Location.class)
					.addAnnotatedClass(Episode.class)
					.buildSessionFactory(serviceRegistry);
		}
		catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}


}
