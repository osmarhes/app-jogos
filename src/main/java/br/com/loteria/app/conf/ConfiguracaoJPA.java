package br.com.loteria.app.conf;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnitUtil;
import javax.sql.DataSource;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import br.com.loteria.app.infra.DozerEventListener;

@Configuration
@EnableJpaRepositories(basePackages = { "br.com.loteria.app.repository" })
@EnableTransactionManagement
public class ConfiguracaoJPA {

	@Bean
	   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	      LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
	      em.setDataSource(dataSource());
	      em.setPackagesToScan(new String[] { "br.com.loteria.app" });
	 
	      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	      em.setJpaVendorAdapter(vendorAdapter);
	      em.setJpaProperties(additionalProperties());
	 
	      return em;
	   }
	 
	   @Bean
	   public DataSource dataSource(){
	      DriverManagerDataSource dataSource = new DriverManagerDataSource();
	      dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	      dataSource.setUrl("jdbc:mysql://localhost:3306/jogo_caixa");
	      dataSource.setUsername( "root" );
	      dataSource.setPassword( "91518135" );
	      return dataSource;
	   }
	 
	   @Bean
	   public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
	      JpaTransactionManager transactionManager = new JpaTransactionManager();
	      transactionManager.setEntityManagerFactory(emf);	 
	      return transactionManager;
	   }
	 
	   @Bean
	   public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
	      return new PersistenceExceptionTranslationPostProcessor();
	   }
	 
	   Properties additionalProperties() {
	      Properties properties = new Properties();
	      properties.setProperty("hibernate.hbm2ddl.auto", "update");
	      properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
	      properties.setProperty("hibernate.show_sql", "true");
	      return properties;
	   }
	   
	   @Bean
		public PersistenceUnitUtil persistenceUnitUtil(EntityManagerFactory emf) {
			return ((JpaTransactionManager) transactionManager(emf))
					.getEntityManagerFactory().getPersistenceUnitUtil();
		}
	   
	   @Bean
		public Mapper beanMapper() {
		   List<DozerEventListener> eventListeners = new ArrayList<>();
			eventListeners.add(new DozerEventListener());
			DozerBeanMapper dozer = new DozerBeanMapper();
			addMapings(dozer);
			dozer.setEventListeners(eventListeners);
			return dozer;
		}


		private void addMapings(DozerBeanMapper dozer) {
			final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
			provider.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*")));
			final Set<BeanDefinition> classes = provider.findCandidateComponents("br.com.loteria.app.infra.loader");
			
			classes.forEach((klazz) -> {
				try {
					Class<?> clazz = Class.forName(klazz.getBeanClassName());
					BeanMappingBuilder bmb = (BeanMappingBuilder)clazz.newInstance();
					dozer.addMapping(bmb);
				} catch (Exception e) {
					throw new RuntimeException("Erro ao carregar mappings do sistema");
				}
			});
		}
}
