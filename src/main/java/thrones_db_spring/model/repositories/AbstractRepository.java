package thrones_db_spring.model.repositories;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Value;
import thrones_db_spring.model.Organization;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by oliverlee
 */
abstract class AbstractRepository {



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



	Properties initProperties() {



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

		return prop;

	}




	<T> List<T> search(Session session, Class<T> cls,String query, String[] fieldList){

		//Session session = factory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<T> cr = cb.createQuery(cls);
		Root<T> orgRoot=cr.from(cls);
		cr.select(orgRoot);

		if(query != null && !query.isEmpty()){

		    Predicate[] predicateList=new Predicate[fieldList.length];
		    for(int i=0;i<fieldList.length;i++){
		        predicateList[i]=cb.like(orgRoot.get(fieldList[i]),query);
            }

		    cr.where(cb.or(predicateList));
/*			cr.where(
					cb.or(
							cb.like(orgRoot.get("name"),query),
							cb.like(orgRoot.get("organizationType"),query),
							cb.like(orgRoot.get("description"),query))
			);*/
		}


		TypedQuery<T> q = session.createQuery(cr);

		List<T> resultList = q.getResultList();
		session.close();
		return resultList;

	}
}
