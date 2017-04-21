package thrones_db_spring.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

/**
 * Created by oliverl1
 */
@Configuration
@EnableAutoConfiguration
public class JpaConfig {


    @Value("${hibernate.c3p0.min_size}")
    private String c3p0_min_size;

    @Value("${hibernate.c3p0.max_size}")
    private String c3p0_max_size;

//    @Value("${hibernate.c3p0.acquire_increment}")
//    private String c3p0_acquire_increment;

    @Value("${hibernate.c3p0.idle_test_period}")
    private String c3p0_idle_test_period;

//    @Value("${hibernate.c3p0.max_statements}")
//    private String c3p0_max_statements;

    @Value("${hibernate.c3p0.timeout}")
    private String c3p0_timeout;



    @Value("${hibernate.connection.url}")
    private String db_url;
    @Value("${hibernate.connection.username}")
    private String db_UN;
    @Value("${hibernate.connection.password}")
    private String db_PW;


    @Value("${hibernate.connection.driver_class}")
    private String driver;
    @Value("${dialect}")
    private String dialect;

    private String scanned_packages="thrones_db_spring.model.pojos.pillars";


    @Bean
    public ComboPooledDataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setMinPoolSize(Integer.parseInt(c3p0_min_size));
        dataSource.setMaxPoolSize(Integer.parseInt(c3p0_max_size));
        //dataSource.setAcquireIncrement(acquireIncrement);
        //dataSource.setMaxStatements(maxStatements);
        dataSource.setMaxIdleTime(Integer.parseInt(c3p0_idle_test_period));
        dataSource.setIdleConnectionTestPeriod(Integer.parseInt(c3p0_idle_test_period));

        dataSource.setJdbcUrl(db_url);
        dataSource.setPassword(db_PW);
        dataSource.setUser(db_UN);
        dataSource.setDriverClass(driver);
        return dataSource;
    }

    @Primary
    @Bean
    public LocalSessionFactoryBean sessionFactory() throws PropertyVetoException {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan(scanned_packages);
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", dialect);
        //hibernateProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        //hibernateProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        sessionFactoryBean.setHibernateProperties(hibernateProperties);

        return sessionFactoryBean;
    }



    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactory.setDataSource(dataSource);
        // Classpath scanning of @Component, @Service, etc annotated class
        entityManagerFactory.setPackagesToScan(scanned_packages);
        // Vendor adapter
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        // Hibernate properties
        Properties additionalProperties = new Properties();
        additionalProperties.put("hibernate.dialect", dialect);
        //additionalProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        //additionalProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));


        entityManagerFactory.setJpaProperties(additionalProperties);

        return entityManagerFactory;
    }

    /**
     * Declare the transaction manager.
     */
    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
        return transactionManager;
    }

    /**
     * PersistenceExceptionTranslationPostProcessor is a bean post processor
     * which adds an advisor to any bean annotated with Repository so that any
     * platform-specific exceptions are caught and then rethrown as one
     * Spring's unchecked data access exceptions (i.e. a subclass of
     * DataAccessException).
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }



    @Autowired
    private DataSource dataSource;

    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;


}
