package com.gbst.test.batchthread.application.component;

import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Component
public class JobRepositoryConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        return executor;
    }

    @Bean
    public JobRepository getJobRepository(PlatformTransactionManager platformTransactionManager) throws Exception{
        JobRepositoryFactoryBean factoryBean = new JobRepositoryFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTransactionManager(platformTransactionManager);
        factoryBean.afterPropertiesSet();

        return (JobRepository)factoryBean.getObject();
    }

    @Bean
    public JobLauncher getJobLauncher(PlatformTransactionManager platformTransactionManager) throws Exception{
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(getJobRepository(platformTransactionManager));
        jobLauncher.setTaskExecutor(threadPoolTaskExecutor());
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
    }



}
