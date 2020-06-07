package com.dev.cinema.config;

import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import java.util.Properties;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan(basePackages = {
        "com.dev.cinema.dao",
        "com.dev.cinema.service"
        }
)
public class AppConfig {
    private final Environment environment;

    @Autowired
    public AppConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public BasicDataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getProperty("db_driver"));
        dataSource.setUrl(environment.getProperty("db_url"));
        dataSource.setPassword(environment.getProperty("db_password"));
        dataSource.setUsername(environment.getProperty("db_username"));
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(getDataSource());

        Properties properties = new Properties();
        properties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getProperty("hibernate.format_sql"));
        properties.put("hibernate.use_sql_comments",
                environment.getProperty("hibernate.use_sql_comments"));
        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));

        factoryBean.setHibernateProperties(properties);
        factoryBean.setAnnotatedClasses(CinemaHall.class);
        factoryBean.setAnnotatedClasses(Movie.class);
        factoryBean.setAnnotatedClasses(MovieSession.class);
        factoryBean.setAnnotatedClasses(Order.class);
        factoryBean.setAnnotatedClasses(ShoppingCart.class);
        factoryBean.setAnnotatedClasses(Ticket.class);
        factoryBean.setAnnotatedClasses(User.class);

        return factoryBean;
    }
}
