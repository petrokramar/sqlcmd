package ua.com.juja.sqlcmd.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
//import ua.com.juja.sqlcmd.service.UserDetailsServiceImpl;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("ua.com.juja.sqlcmd")
@ComponentScan("ua.com.juja.sqlcmd")
public class TestAppConfig {
//    @Bean
//    public DataSource logDataSource(){
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.postgresql.Driver");
//        dataSource.setUrl("jdbc:postgresql://localhost:8757/sqlcmd_log");
//        dataSource.setUsername("postgres");
//        dataSource.setPassword("123456");
//        return dataSource;
//    }
//
////    @Bean
////    public EntityManagerFactory entityManagerFactory(){
////        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
////        vendorAdapter.setGenerateDdl(true);
////        Properties jpaProperties = getHibernateProperties();
////        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
////        factory.setDataSource(logDataSource());
////        factory.setPackagesToScan("ua.com.juja.sqlcmd");
////        factory.setJpaVendorAdapter(vendorAdapter);
////        factory.setJpaProperties(jpaProperties);
////        factory.afterPropertiesSet();
////        return factory.getObject();
////    }
//
//    @Bean
//    public SessionFactory sessionFactory() {
//        LocalSessionFactoryBuilder builder =
//                new LocalSessionFactoryBuilder(logDataSource());
//        builder.scanPackages("ua.com.juja.sqlcmd")
//                .addProperties(getHibernateProperties());
//        return builder.buildSessionFactory();
//    }
//
//    private Properties getHibernateProperties() {
//        Properties prop = new Properties();
//        prop.put("hibernate.hbm2ddl.auto", "validate");
//        prop.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//        prop.put("hibernate.format_sql", "true");
//        prop.put("hibernate.show_sql", "true");
//        return prop;
//    }
//
////    @Bean
////    public PlatformTransactionManager transactionManager(){
////        JpaTransactionManager txManager = new JpaTransactionManager();
////        txManager.setEntityManagerFactory(entityManagerFactory());
////        return txManager;
////    }
}