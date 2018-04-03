package com.gbst.test.batchthread.application.batch;

import com.gbst.test.batchthread.application.component.JobRequestExecutionListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepListener;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowJobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.core.repository.support.SimpleJobRepository;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class JobStarter {
    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    JobExecutionListener jobExecutionListener;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    Step partitionStep1;


    @Autowired
    Step partitionStep2;


    @Autowired
    Step partitionStep3;


    @Autowired
    Step partitionStep4;

    @Autowired
    Step partitionStep5;


    @Autowired
    Tasklet postStepTasklet;

    @Autowired
    StepListener stepListener;
    
    @Autowired
    JobRequestExecutionListener jobRequestExecutionListener;

    @Bean
    public Job batchJob(){
       return  getAllJob();
    }

    private Job getAllJob(){
        
        Step postTask = steps.get("postTask")
                .tasklet(postStepTasklet)
                .build();

        return jobBuilderFactory.get("allJob")
                .start(partitionStep1)
                .next(postTask)
                .next(partitionStep2)
                .next(postTask)
                .next(partitionStep3)
                .next(postTask)
                .next(partitionStep4)
                .next(postTask)
                .next(partitionStep5)
                .next(postTask)
                .listener(jobRequestExecutionListener)
                .build();


    }
}
