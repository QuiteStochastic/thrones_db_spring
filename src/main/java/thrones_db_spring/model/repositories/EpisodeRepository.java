package thrones_db_spring.model.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import thrones_db_spring.model.Episode;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Created by oliverlee
 */
@Repository
public class EpisodeRepository {

	//private SessionFactory factory;

	@Autowired
	private EntityManagerFactory entityManagerFactory;

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
		EntityManager em = entityManagerFactory.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Episode> cr = cb.createQuery(Episode.class);
		TypedQuery<Episode> q = em.createQuery(cr);
		List<Episode> episodeList = q.getResultList();
		em.close();
		return episodeList;

	}
}
