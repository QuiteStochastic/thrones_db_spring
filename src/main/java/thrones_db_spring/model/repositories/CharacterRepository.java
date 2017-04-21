package thrones_db_spring.model.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import thrones_db_spring.model.pojos.pillars.Character;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by oliverlee
 */
@Repository
public class CharacterRepository extends AbstractRepository{

	@Autowired
	private SessionFactory factory;

	/*@PostConstruct
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
                    .addAnnotatedClass(Organization.class)
                    .addAnnotatedClass(Location.class)
                    .addAnnotatedClass(Event.class)
                    .addAnnotatedClass(Episode.class)
                    .buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}*/


	public List<Character> getAllCharacters(){
		Session session = factory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Character> cr = cb.createQuery(Character.class);
		Root<Character> characterRoot=cr.from(Character.class);
        //cr.multiselect(characterRoot.get("characterId"),characterRoot.get("firstName"),characterRoot.get("lastName"));

		cr.select(characterRoot);


		TypedQuery<Character> q = session.createQuery(cr);
		List<Character> characterList = q.getResultList();
		session.close();
		return characterList;

	}


	public Character getCharacterById(Integer charId){
		Session session = factory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Character> cr = cb.createQuery(Character.class);
		Root<Character> characterRoot=cr.from(Character.class);
		cr.select(characterRoot);
        cr.where(cb.equal(characterRoot.get("characterId"),charId));


		TypedQuery<Character> q = session.createQuery(cr);
		Character character = q.getSingleResult();
		session.close();
		return character;

	}


	public List<Character> search(String query){

		Session session = factory.openSession();

		String[] fieldList={"firstName","lastName","alias","description"};

		return super.search(session, Character.class, query, fieldList);
	}


}
