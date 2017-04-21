package thrones_db_spring.model.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import thrones_db_spring.model.pojos.pillars.Event;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by oliverlee
 */
@Repository
public class EventRepository extends AbstractRepository{

    @Autowired
    private SessionFactory factory;


/*
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
*/




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
