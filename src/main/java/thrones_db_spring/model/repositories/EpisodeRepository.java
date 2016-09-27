package thrones_db_spring.model.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Repository;
import thrones_db_spring.model.Episode;

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
public class EpisodeRepository extends AbstractRepository {

	private SessionFactory factory;


	//private EntityManagerFactory entityManagerFactory;

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



	public Episode getEpisodeById(Integer episodeId){
		Session session = factory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Episode> cr = cb.createQuery(Episode.class);
		Root<Episode> episodeRoot=cr.from(Episode.class);
		cr.select(episodeRoot);
		cr.where(cb.equal(episodeRoot.get("episodeId"),episodeId));


		TypedQuery<Episode> q = session.createQuery(cr);
		Episode episode = q.getSingleResult();
		session.close();
		return episode;

	}

}
