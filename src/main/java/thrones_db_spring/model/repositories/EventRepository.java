package thrones_db_spring.model.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Repository;
import thrones_db_spring.model.Character;
import thrones_db_spring.model.*;

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
                    .addAnnotatedClass(Character.class)
                    .addAnnotatedClass(Organization.class)
                    .buildSessionFactory(serviceRegistry);
		}
		catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}




    public List<Event> getAllEvents(){
        Session session = factory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Event> cr = cb.createQuery(Event.class);
        Root<Event> eventRoot=cr.from(Event.class);
        cr.select(eventRoot);


        TypedQuery<Event> q = session.createQuery(cr);
        List<Event> eventList = q.getResultList();
        session.close();
        return eventList;

    }



    public Event getEventById(Integer eventId){
        Session session = factory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Event> cr = cb.createQuery(Event.class);
        Root<Event> eventRoot=cr.from(Event.class);
        cr.select(eventRoot);
        cr.where(cb.equal(eventRoot.get("eventId"),eventId));


        TypedQuery<Event> q = session.createQuery(cr);
        Event event= q.getSingleResult();
        session.close();
        return event;

    }


    public List<Event> search(String query){

        Session session = factory.openSession();

        String[] fieldList={"name","eventType","description"};

        return super.search(session, Event.class, query, fieldList);
    }

}
