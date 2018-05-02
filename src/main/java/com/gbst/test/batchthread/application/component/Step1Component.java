package com.gbst.test.batchthread.application.component;

import com.gbst.test.batchthread.application.model.BatchData;
import com.gbst.test.batchthread.application.repository.BatchDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class Step1Component {
    private final Logger logger = LoggerFactory.getLogger(Step1Component.class);

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
                batchDataRepository.updateLastStep();
                return null;
            }
        };
    }




    @Bean
    public ItemWriter<BatchData> step1ItemWriter(){
        JpaItemWriter<BatchData> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }

    @Bean
    @StepScope
    public ItemProcessListener<Integer,BatchData> step1ItemProcessorListener(@Value("#{stepExecution}") StepExecution stepExecution){

        return new ItemProcessListener<Integer, BatchData>() {

            private LocalDateTime start;

            @Override
            public void beforeProcess(Integer integer) {
                start = LocalDateTime.now();
            }

            @Override
            public void afterProcess(Integer integer, BatchData batchData) {
                batchData.setJobStart(start);
                batchData.setJobEnd(LocalDateTime.now());
                batchData.setJobExecutionId(stepExecution.getJobExecutionId());
            }

            @Override
            public void onProcessError(Integer integer, Exception e) {

            }
        };
    }

}
