package com.nhndooray.samplespringbatch.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

@Configuration

public class DataBaseConfig {

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:13306/ad_batch?serverTimezone=UTC");
        dataSource.setUsername("dev_user");
        dataSource.setPassword("devuser0122##@");
        return dataSource;
    }

    @Bean
    public TransactionManager transactionManager(){
        return null;
    }
}
