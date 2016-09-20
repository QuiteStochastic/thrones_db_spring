package thrones_db_spring.model.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import thrones_db_spring.model.Episode;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Properties;

/**
 * Created by oliverlee
 */
@Repository
public class EpisodeRepository {

	private SessionFactory factory;


	//private EntityManagerFactory entityManagerFactory;


	@Value("${hibernate.connection.driver_class}")
	private String driver;

	@Value("${dialect}")
	private String dialect;

	@Value("${hibernate.connection.url}")
	private String db_url;

	/** The database username. */
	@Value("${hibernate.connection.username}")
	private String db_UN;

	/** The database password. */
	@Value("${hibernate.connection.password}")
	private String db_PW;

	@PostConstruct
	public void initFactory(){

		try {
			Properties prop= new Properties();
			prop.setProperty("hibernate.connection.driver_class",driver);
			prop.setProperty("dialect", dialect);
			prop.setProperty("hibernate.c3p0.min_size", "10");
			prop.setProperty("hibernate.c3p0.max_size","20");
			prop.setProperty("hibernate.c3p0.acquire_increment","1");
			prop.setProperty("hibernate.c3p0.idle_test_period","3000");
			prop.setProperty("hibernate.c3p0.max_statements", "100");
			prop.setProperty("hibernate.c3p0.timeout","1800");
			prop.setProperty("hibernate.connection.url", db_url);
			prop.setProperty("hibernate.connection.username", db_UN);
			prop.setProperty("hibernate.connection.password", db_PW);
			prop.setProperty("hibernate.current_session_context_class", "org.hibernate.context.internal.ThreadLocalSessionContext");
			ServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder()
					.applySettings( prop )
					.build();
			factory = new Configuration()
					.addPackage("thrones_db_spring.model")
					.addProperties(prop)
					.addAnnotatedClass(Episode.class)
					.buildSessionFactory(serviceRegistry);
		}
		catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

/*	public List<Episode> getAllEpisodes(){
		//Session session = factory.openSession();

		EntityManager em = entityManagerFactory.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Episode> cr = cb.createQuery(Episode.class);
		//CriteriaQuery<Episode> cr = session.getCriteriaBuilder().createQuery(Episode.class);


		Root<Episode> episodeRoot=cr.from(Episode.class);
		cr.select(episodeRoot);
		TypedQuery<Episode> q = em.createQuery(cr);


		//session.beginTransaction();
		List<Episode> episodeList = q.getResultList();
		//session.getTransaction().commit();
		//session.close();
		em.close();

		return episodeList;

	}*/

	public List<Episode> getAllEpisodes(){
		Session session = factory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Episode> cr = cb.createQuery(Episode.class);
		Root<Episode> episodeRoot=cr.from(Episode.class);
		cr.select(episodeRoot);


		TypedQuery<Episode> q = session.createQuery(cr);
		List<Episode> episodeList = q.getResultList();
		session.close();
		return episodeList;

	}
}
