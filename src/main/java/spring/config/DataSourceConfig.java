package spring.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:jdbc.properties")
public class DataSourceConfig {

	@Bean
	public DataSource dataSource(@Value("${jdbc.driverClassName}") String driverClassName,
			@Value("${jdbc.url}") String url,
			@Value("${jdbc.username}") String username,
			@Value("${jdbc.password}") String password) {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setJdbcUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}

	@Bean
	public DatabasePopulator databasePopulator(DataSource dataSource) {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("schema.sql"));
		// populator.addScript(new ClassPathResource("data.sql"));
		return populator;
	}

	@Bean
	public DataSourceInitializer dataSourceInitializer(DatabasePopulator databasePopulator, DataSource dataSource) {
		DataSourceInitializer initializer = new DataSourceInitializer();
		initializer.setDatabasePopulator(databasePopulator);
		initializer.setDataSource(dataSource);
		return initializer;
	}
}
