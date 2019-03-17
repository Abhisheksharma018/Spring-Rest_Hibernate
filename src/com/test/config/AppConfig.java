package com.test.config;

import java.util.Properties;

import javax.sql.DataSource;

import static org.hibernate.cfg.Environment.*;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration

@EnableTransactionManagement
@ComponentScan({"com.test.config"})
public class AppConfig {
	
	@Autowired
	private Environment env;
	
	@Bean
	   public LocalSessionFactoryBean getSessionFactory() {
	      LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

	      /*Properties props = new Properties();
	      // Setting JDBC properties
	      props.put(DRIVER, env.getProperty("mysql.driver"));
	      props.put(URL, env.getProperty("mysql.url"));
	      props.put(USER, env.getProperty("mysql.user"));
	      props.put(PASS, env.getProperty("mysql.password"));

	      // Setting Hibernate properties
	      props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
	      props.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
	      
	      factoryBean.setHibernateProperties(props);
	      factoryBean.setPackagesToScan("com.test.model");*/
	      
	      factoryBean.setDataSource(dataSource());
	      factoryBean.setPackagesToScan(new String[] {"com.test.model"});
	      factoryBean.setHibernateProperties(hibernateProperties());

	      return factoryBean;
	   }
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/test");
		ds.setUsername("root");
		ds.setPassword("root");
		return ds;
	}
	
		private Properties hibernateProperties() {
			Properties properties = new Properties();
			properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
			properties.put("hibernate.show_sql", "true");
			properties.put("hibernate.format_sql", "false");
			return properties;
		}
		@Bean
		@Autowired
		   public HibernateTransactionManager transactionManager(SessionFactory s) {
		      HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		      transactionManager.setSessionFactory(s);
		      return transactionManager;
		   }
	
	  /* @Bean
	   public HibernateTransactionManager getTransactionManager() {
	      HibernateTransactionManager transactionManager = new HibernateTransactionManager();
	      transactionManager.setSessionFactory(getSessionFactory().getObject());
	      return transactionManager;
	   }*/
}
