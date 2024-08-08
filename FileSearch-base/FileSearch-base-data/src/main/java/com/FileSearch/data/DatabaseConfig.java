package com.FileSearch.data;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.SQLException;

@Configuration
@PropertySource("classpath:application.properties")
public class DatabaseConfig {
    @Resource
    private Environment env;

    //创建数据库
    //设置数据库driver，url，用户名及密码
    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        //ComboPooledDataSource creates and manages connections to the database
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(env.getRequiredProperty("db.driver"));
        dataSource.setJdbcUrl(env.getRequiredProperty("db.url"));
        dataSource.setUser(env.getRequiredProperty("db.username"));
        dataSource.setPassword(env.getRequiredProperty("db.password"));
        return dataSource;
    }

    //建立JDBC的连接
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) throws SQLException {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) throws SQLException {
        return new DataSourceTransactionManager(dataSource);
    }
}
