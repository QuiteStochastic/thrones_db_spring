package thrones_db_spring.model.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import thrones_db_spring.model.pojos.pillars.Location;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by oliverlee
 */
@Repository
public class LocationRepository extends AbstractRepository{

    @Autowired
    private SessionFactory factory;


	/*@PostConstruct
	public void initFactory(){

		Properties prop= super.initProperties();

		try {
			ServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder()
					.applySettings( prop )
					.build();
			factory = new Configuration()
					.addPackage("thrones_db_spring.model")
					.addProperties(prop)
					.addAnnotatedClass(Location.class)
                    .addAnnotatedClass(Character.class)
                    .addAnnotatedClass(Event.class)
                    .addAnnotatedClass(Organization.class)
                    .addAnnotatedClass(Episode.class)
                    .buildSessionFactory(serviceRegistry);
		}
		catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}*/




    public List<Location> getAllLocations(){
        Session session = factory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Location> cr = cb.createQuery(Location.class);
        Root<Location> locationRoot=cr.from(Location.class);
        cr.select(locationRoot);


        TypedQuery<Location> q = session.createQuery(cr);
        List<Location> locationList = q.getResultList();
        session.close();
        return locationList;

    }



    public Location getLocationById(Integer locationId){
        Session session = factory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Location> cr = cb.createQuery(Location.class);
        Root<Location> locationRoot=cr.from(Location.class);
        cr.select(locationRoot);
        cr.where(cb.equal(locationRoot.get("locationId"),locationId));


        TypedQuery<Location> q = session.createQuery(cr);
        Location location= q.getSingleResult();
        session.close();
        return location;

    }


    public List<Location> search(String query){

        Session session = factory.openSession();

        String[] fieldList={"name","locationType","description"};

        return super.search(session, Location.class, query, fieldList);
    }



}
