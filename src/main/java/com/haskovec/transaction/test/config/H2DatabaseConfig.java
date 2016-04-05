package com.haskovec.transaction.test.config;

import com.atomikos.jdbc.nonxa.AtomikosNonXADataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by jhaskovec on 4/5/16.
 */
@Configuration
public class H2DatabaseConfig {

	@Bean
	public DataSource getAtomikosNonXADataSource() {
		AtomikosNonXADataSourceBean ds = new AtomikosNonXADataSourceBean();
		ds.setUniqueResourceName("h2");
		ds.setDriverClassName("org.h2.Driver");
		ds.setUrl("jdbc:h2:~/test;DB_CLOSE_ON_EXIT=FALSE");
		ds.setUser("sa");
		ds.setPassword("saPassword");
		ds.setPoolSize(3);

		return ds;
	}

}
