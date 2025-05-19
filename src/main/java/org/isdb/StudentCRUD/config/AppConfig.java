package org.isdb.StudentCRUD.config;

import java.time.Duration;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class AppConfig {

	@Bean
	@Profile("isdb")
	public DataSource dataSourceIsdb() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521/ORCLPDB");
		dataSource.setUsername("orclpdbuser");
		dataSource.setPassword("isdb62");
		dataSource.setInitialSize(5); // Initial connections in the pool
		dataSource.setMaxTotal(20); // Maximum number of active connections
		dataSource.setMaxIdle(10); // Maximum idle connections
		dataSource.setMinIdle(5); // Minimum idle connections
		dataSource.setMaxWait(Duration.ofMillis(10000));
		return dataSource;
	}

	@Bean
	@Profile("home")
	public DataSource dataSourceHome() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres");
		dataSource.setInitialSize(5); // Initial connections in the pool
		dataSource.setMaxTotal(20); // Maximum number of active connections
		dataSource.setMaxIdle(10); // Maximum idle connections
		dataSource.setMinIdle(5); // Minimum idle connections
		dataSource.setMaxWait(Duration.ofMillis(10000));
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}
