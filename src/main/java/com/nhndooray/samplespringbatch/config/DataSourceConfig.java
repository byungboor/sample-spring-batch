package com.nhndooray.samplespringbatch.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                                .type(HikariDataSource.class)
                                .url("jdbc:h2:tcp://localhost/~/batch")
                                .driverClassName("org.h2.Driver")
                                .username("sa")
                                .build();
    }
}
