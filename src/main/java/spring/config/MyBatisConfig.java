package spring.config;


import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "spring.persistence.mapper")
public class MyBatisConfig {

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);

		factoryBean.setTypeAliasesPackage("spring.persistence.entity");
		org.apache.ibatis.session.Configuration configuration =
				new org.apache.ibatis.session.Configuration();

		configuration.setMapUnderscoreToCamelCase(true);
		factoryBean.setConfiguration(configuration);
		return factoryBean;
	}
}
