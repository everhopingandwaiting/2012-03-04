package qa.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import qa.dao.TagDao;
import qa.dao.UserDao;
import qa.dao.VoteDao;
import qa.dao.WordsDao;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:datasource.properties")
public class DaoConfiguration {
    @Autowired
    private Environment environment;

    @Bean
    public UserDao userDao(HibernateTemplate template) {
        return new UserDao(template);
    }

    @Bean
    public VoteDao voteDao(HibernateTemplate template) {
        return new VoteDao(template);
    }

    @Bean
    public WordsDao wordsDao(HibernateTemplate template) {
        return new WordsDao(template);
    }

    @Bean
    public TagDao tagDao(HibernateTemplate template) {
        return new TagDao(template);
    }

    @Bean
    public HibernateTemplate hibernateTemplate(SessionFactory sessionFactory) {
        return new HibernateTemplate(sessionFactory);
    }

    @Bean
    public SessionFactory sessionFactory(DataSource dataSource) {
        return new LocalSessionFactoryBuilder(dataSource)
                .scanPackages("qa.domain")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
                .setProperty("hibernate.hbm2ddl.auto", "update")
                .setProperty("hibernate.show_sql", "true")
                .buildSessionFactory();
    }

    @Bean
    @Profile("prod")
    @Primary
    public DataSource prodDataSource() { //TODO replace DriverManagerDataSource to support connection pool etc.
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(environment.getProperty("datasource.url"));
        dataSource.setDriverClassName(environment.getProperty("datasource.driver"));
        dataSource.setUsername(environment.getProperty("datasource.user"));
        dataSource.setPassword(environment.getProperty("datasource.password"));
        return dataSource;
    }

    @Bean(destroyMethod = "shutdown", name = "dataSource")
    @Profile("dev")
    public DataSource devDataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("devdb")
                .build();
    }

    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}
