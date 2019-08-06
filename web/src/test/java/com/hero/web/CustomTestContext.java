package com.hero.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.AbstractTestExecutionListener;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @author carl
 */
@Slf4j
@Configuration
@ComponentScan("com.hero")
@PropertySource("classpath:application.yml")
@TestExecutionListeners(CustomTestContext.CustomTestExecutionListener.class)
public class CustomTestContext {

    @Bean
    public TestExecutionListener customTestExecutionListener() {
        return new CustomTestExecutionListener();
    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    class CustomTransactionManager extends AbstractPlatformTransactionManager {

        @Override
        protected Object doGetTransaction() throws TransactionException {
            return null;
        }

        @Override
        protected void doBegin(Object transaction, TransactionDefinition definition) throws TransactionException {

        }

        @Override
        protected void doCommit(DefaultTransactionStatus status) throws TransactionException {

        }

        @Override
        protected void doRollback(DefaultTransactionStatus status) throws TransactionException {

        }
    }

    static class CustomTestExecutionListener extends AbstractTestExecutionListener {

        @Override
        public void beforeTestMethod(TestContext testContext) throws Exception {
            log.info("before test method: " + testContext.getTestMethod().getName());
        }

        @Override
        public void afterTestMethod(TestContext testContext) throws Exception {
            log.info("after test method: " + testContext.getTestMethod().getName());
        }
    }
}
