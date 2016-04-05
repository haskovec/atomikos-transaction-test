package com.haskovec.transaction.test.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

/**
 * Created by jhaskovec on 4/5/16.
 */
@Configuration
@EnableTransactionManagement
public class TransactionConfig implements TransactionManagementConfigurer {
	private static final Logger log = LoggerFactory.getLogger(TransactionConfig.class);

	@Autowired
	private DataSource datasource;

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		final UserTransaction userTransaction = new UserTransactionImp();
		final TransactionManager transactionManager = new UserTransactionManager();

		final JtaTransactionManager jtaTransactionManager = new JtaTransactionManager(userTransaction, transactionManager);

		return jtaTransactionManager;
	}

	@Bean
	JdbcTemplate jdbcTemplate(DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		log.info("Creating tables");
		jdbcTemplate.execute("drop table example if exists");
		jdbcTemplate.execute("CREATE TABLE example ( id INT(10), name varchar(32), PRIMARY KEY(id))");
		return jdbcTemplate;
	}
}
