package ru.job4j.accidents.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;


/**
 * Класс для соединения с БД.
 *
 * На данном этапе написано все через JDBC. Spring напрямую не работает с базой. Он использует JDBC библиотеки.
 * Spring оборачивает JDBC в свои классы, делая их удобными для работы. Здесь используется шаблон "Декоратор".
 *
 * Содержит бин, который будет содержать пул соединений.
 * Метод ds загружает пул соединений.
 * Метод jdbc создает обертку для работы с базой.
 */
@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class JdbcConfig {

    @Bean
    public DataSource ds(@Value("${jdbc.driver}") String driver,
                         @Value("${jdbc.url}") String url,
                         @Value("${jdbc.username}") String username,
                         @Value("${jdbc.password}") String password) {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }

    @Bean
    public JdbcTemplate jdbc(DataSource ds) {
        return new JdbcTemplate(ds);
    }
}
