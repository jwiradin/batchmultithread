package com.gbst.test.batchthread.application.component;

import com.gbst.test.batchthread.application.model.BatchData;
import com.gbst.test.batchthread.application.repository.BatchDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;

@Component
public class StepComponent {
    private final Logger logger = LoggerFactory.getLogger(StepComponent.class);

    @Autowired
    BatchDataRepository batchDataRepository;

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @StepScope
    @Bean
    public Tasklet postStepTasklet(){
        return  new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

                //batchDataRepository.updateLastStep();
                return null;
            }
        };
    }

    @Bean
    public ItemWriter<BatchData> stepItemWriter(){
        JpaItemWriter<BatchData> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }

}
