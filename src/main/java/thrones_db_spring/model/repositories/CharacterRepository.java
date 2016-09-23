package thrones_db_spring.model.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Repository;
import thrones_db_spring.model.Character;

import javax.annotation.PostConstruct;
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
public class CharacterRepository extends AbstractRepository{

	private SessionFactory factory;

	@PostConstruct
	public void initFactory() {

		Properties prop = super.initProperties();


		try {
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(prop)
					.build();
			factory = new Configuration()
					.addPackage("thrones_db_spring.model")
					.addProperties(prop)
					.addAnnotatedClass(Character.class)
					.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}


	public List<Character> getAllCharacters(){
		Session session = factory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Character> cr = cb.createQuery(Character.class);
		Root<Character> characterRoot=cr.from(Character.class);
		cr.select(characterRoot);


		TypedQuery<Character> q = session.createQuery(cr);
		List<Character> characterList = q.getResultList();
		session.close();
		return characterList;

	}

}
