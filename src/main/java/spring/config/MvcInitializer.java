package spring.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class MvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

		@Override
		protected Class<?>[] getRootConfigClasses() {
			return null;
		}

		@Override
		protected Class<?>[] getServletConfigClasses() {
			return new Class<?>[] {DataSourceConfig.class, MyBatisConfig.class, ServiceConfig.class, MvcConfig.class, DAOConfig.class};
		}
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

}
