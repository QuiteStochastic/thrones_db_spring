package thrones_db_spring.model.repositories;

import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import java.util.List;

/**
 * Created by oliverlee
 */
public abstract class AbstractRepository {



/*	@Value("${hibernate.connection.driver_class}")
	private String driver;

	@Value("${dialect}")
	private String dialect;

	@Value("${hibernate.connection.url}")
	private String db_url;

	*//** The database username. *//*
	@Value("${hibernate.connection.username}")
	private String db_UN;

	*//** The database password. *//*
	@Value("${hibernate.connection.password}")
	private String db_PW;*/


//    @Value("${hibernate.c3p0.min_size}")
//    private String c3p0_min_size;
//
//    @Value("${hibernate.c3p0.max_size}")
//    private String c3p0_max_size;
//
//    @Value("${hibernate.c3p0.acquire_increment}")
//    private String c3p0_acquire_increment;
//
//    @Value("${hibernate.c3p0.idle_test_period}")
//    private String c3p0_idle_test_period;
//
//    @Value("${hibernate.c3p0.max_statements}")
//    private String c3p0_max_statements;
//
//    @Value("${hibernate.c3p0.timeout}")
//    private String c3p0_timeout;


/*	Properties initProperties() {



		Properties prop= new Properties();
		prop.setProperty("hibernate.connection.driver_class",driver);
		prop.setProperty("dialect", dialect);

//		prop.setProperty("hibernate.c3p0.min_size", c3p0_min_size);
//		prop.setProperty("hibernate.c3p0.max_size", c3p0_max_size);
//		prop.setProperty("hibernate.c3p0.acquire_increment",c3p0_acquire_increment);
//		prop.setProperty("hibernate.c3p0.idle_test_period",c3p0_idle_test_period);
//		prop.setProperty("hibernate.c3p0.max_statements", c3p0_max_statements);
//		prop.setProperty("hibernate.c3p0.timeout",c3p0_timeout);

		prop.setProperty("hibernate.connection.url", db_url);
		prop.setProperty("hibernate.connection.username", db_UN);
		prop.setProperty("hibernate.connection.password", db_PW);
		//prop.setProperty("hibernate.connection.pool_size","100");
		//prop.setProperty("hibernate.current_session_context_class", "org.hibernate.context.internal.ThreadLocalSessionContext");

		return prop;

	}*/




	<T> List<T> search(Session session, Class<T> cls,String query, String[] fieldList){

		//Session session = factory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<T> cr = cb.createQuery(cls);
        EntityType<T> entityType = session.getMetamodel().entity(cls);
		Root<T> root=cr.from(cls);
		cr.select(root);

		if(query != null && !query.isEmpty()){

		    Predicate[] predicateList=new Predicate[fieldList.length];
		    for(int i=0;i<fieldList.length;i++){
		        //predicateList[i]=cb.like(root.get(fieldList[i]),query);
                predicateList[i]=cb.like(
                        cb.lower(root.get(entityType.getDeclaredSingularAttribute(fieldList[i], String.class))),
                        query);
            }

		    cr.where(cb.or(predicateList));
		}


		TypedQuery<T> q = session.createQuery(cr);

		List<T> resultList = q.getResultList();
		session.close();
		return resultList;

	}

    public static String sanitize(String s){

        String result=s;
        result=result.replaceAll("@","");
        result=result.replaceAll("-","");
        result=result.replaceAll("\\.","");
        result=result.replaceAll("!","");
        result=result.replaceAll("\\?","");
        result=result.replaceAll(",","");
        result=result.replaceAll("<","");
        result=result.replaceAll(">","");
        result=result.replaceAll("=","");
        result=result.replaceAll(";","");
        result=result.replaceAll("\"","");
        result=result.replaceAll("'","");
        result=result.replaceAll("\\\\","");
        result=result.replaceAll("\\n","");
        result=result.replaceAll("\\t","");

        result=result.replaceAll("_"," ");
        result=result.trim();

        result=result.toLowerCase();

        result="%"+result +"%";


        return result;
    }


}
