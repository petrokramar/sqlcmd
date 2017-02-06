package ua.com.juja.sqlcmd.config;

import org.hibernate.SessionFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import ua.com.juja.sqlcmd.controller.PropertyHandler;
//import ua.com.juja.sqlcmd.service.UserDetailsServiceImpl;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("ua.com.juja.sqlcmd")
@EnableTransactionManagement
@ComponentScan("ua.com.juja.sqlcmd")
public class AppConfig {
    PropertyHandler settings = PropertyHandler.getInstance();

    @Bean
    public DataSource logDataSource(){
        //TODO Change DriverManagerDataSource to BasicDataSource
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        if (System.getenv("DATABASE_URL") != null) {
            URI dbUri;
            try {
                dbUri = new URI(System.getenv("DATABASE_URL"));
            } catch (URISyntaxException e) {
                throw new RuntimeException("Error creating database URI");
            }
            dataSource.setDriverClassName("org.postgresql.Driver");
            dataSource.setUrl("jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() +
            "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory");
            dataSource.setUsername(dbUri.getUserInfo().split(":")[0]);
            dataSource.setPassword(dbUri.getUserInfo().split(":")[1]);
        } else {
            dataSource.setDriverClassName(settings.getProperty("log.database.driver"));
            dataSource.setUrl(settings.getProperty("log.database.url"));
            dataSource.setUsername(settings.getProperty("log.database.user.name"));
            dataSource.setPassword(settings.getProperty("log.database.user.password"));
        }

        return dataSource;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory(){
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        Properties jpaProperties = getHibernateProperties();
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(logDataSource());
        factory.setPackagesToScan("ua.com.juja.sqlcmd");
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setJpaProperties(jpaProperties);
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder builder =
                new LocalSessionFactoryBuilder(logDataSource());
        builder.scanPackages("ua.com.juja.sqlcmd")
                .addProperties(getHibernateProperties());
        return builder.buildSessionFactory();
    }

//    @Bean
//    public LocalSessionFactoryBean sessionFactory(){
//        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
//        localSessionFactoryBean.setDataSource(logDataSource());
//        localSessionFactoryBean.setPackagesToScan("ua.com.juja.sqlcmd");
//        localSessionFactoryBean.setHibernateProperties(getHibernateProperties());
//        return localSessionFactoryBean;
//    }

    private Properties getHibernateProperties() {
        Properties prop = new Properties();
        //TODO remove hardcode
//        prop.put("hibernate.hbm2ddl.auto", settings.getProperty("hibernate.hbm2ddl.auto"));
//        prop.put("hibernate.dialect", settings.getProperty("hibernate.dialect"));
//        prop.put("hibernate.format_sql", settings.getProperty("hibernate.format_sql"));
//        prop.put("hibernate.show_sql", settings.getProperty("hibernate.show_sql"));
        prop.put("hibernate.hbm2ddl.auto", "create");
        prop.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
        prop.put("hibernate.format_sql", "true");
        prop.put("hibernate.show_sql", "true");
        return prop;
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }

//    @Bean
//    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
//        HibernateTransactionManager txManager = new HibernateTransactionManager();
//        txManager.setSessionFactory(sessionFactory);
//        return txManager;
//    }


}