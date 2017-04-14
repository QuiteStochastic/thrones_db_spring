package thrones_db_spring.model.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Repository;
import thrones_db_spring.model.*;
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
public class OrganizationRepository extends AbstractRepository{

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
					.addAnnotatedClass(Organization.class)
					.addAnnotatedClass(Location.class)
                    .addAnnotatedClass(Character.class)
                    .addAnnotatedClass(Event.class)
                    .addAnnotatedClass(Episode.class)
                    .buildSessionFactory(serviceRegistry);
		}
		catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}


	public List<Organization> getAllOrganizations(){
		Session session = factory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Organization> cr = cb.createQuery(Organization.class);
		Root<Organization> orgRoot=cr.from(Organization.class);
		cr.select(orgRoot);


		TypedQuery<Organization> q = session.createQuery(cr);
		List<Organization> organizationList = q.getResultList();
		session.close();
		return organizationList;

	}



	public Organization getOrganizationById(Integer organizationId){
		Session session = factory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Organization> cr = cb.createQuery(Organization.class);
		Root<Organization> orgRoot=cr.from(Organization.class);
		cr.select(orgRoot);
		cr.where(cb.equal(orgRoot.get("organizationId"),organizationId));


		TypedQuery<Organization> q = session.createQuery(cr);
		Organization organization = q.getSingleResult();
		session.close();
		return organization;

	}



	public List<Organization> search(String query){

        Session session = factory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Organization> cr = cb.createQuery(Organization.class);
        Root<Organization> orgRoot=cr.from(Organization.class);
        cr.select(orgRoot);

        if(query != null && !query.isEmpty()){
            cr.where(
                    cb.or(
                            cb.like(orgRoot.get("name"),query),
                            cb.like(orgRoot.get("organizationType"),query),
                            cb.like(orgRoot.get("description"),query))
                    );
        }


        TypedQuery<Organization> q = session.createQuery(cr);

        List<Organization> organizationList = q.getResultList();
        session.close();
        return organizationList;

    }
}
