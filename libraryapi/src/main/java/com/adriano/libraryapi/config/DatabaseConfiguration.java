package com.adriano.libraryapi.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

    @Configuration
    public class DatabaseConfiguration {

        @Value("${spring.datasource.url}")
        String url;
        @Value("${spring.datasource.username}")
        String username;
        @Value("${spring.datasource.password}")
        String password;
        @Value("${spring.datasource.driver-class-name}")
        String driverClassName;

//        @Bean
        public DataSource dataSource() {
            DriverManagerDataSource ds = new DriverManagerDataSource();
            ds.setUrl(url);
            ds.setUsername(username);
            ds.setPassword(password);
            ds.setDriverClassName(driverClassName);

            return ds;
        }

        /*
        * Configuração do HikariCP
        * https://github.com/brettwooldridge/HikariCP
        * */
        @Bean
        public DataSource hikariDataSource() {
            HikariConfig config = new HikariConfig();
            config.setUsername(username);
            config.setPassword(password);
            config.setDriverClassName(driverClassName);
            config.setJdbcUrl(url);

            // quantidade de conexões que o hikari vai manter abertas
            config.setMaximumPoolSize(10);
            // tamanho inicial do pool - inicia aberta
            config.setMinimumIdle(1);
            // nome do pool
            config.setPoolName("library-db-pool");
            // Tempo de vida do pool em ms. padrão 30min(1800000)
            config.setMaxLifetime(600000); // 10min em ms
            // Tempo de espera para obter uma conexão. 1.6min
            config.setConnectionTimeout(100000);
            // teste rápido para saber se o banco esta funcionando
            config.setConnectionTestQuery("SELECT 1");

            return new HikariDataSource(config);
        }
    }

