package com.gbst.test.batchthread.application.batch;

import com.gbst.test.batchthread.application.component.StepItemProcessorListener;
import com.gbst.test.batchthread.application.component.StepItemWriterListener;
import com.gbst.test.batchthread.application.model.BatchData;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepListener;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class StepConfiguration {

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    ItemReader<Integer> step1ItemReader;

    @Autowired
    ItemReader<Integer> step2ItemReader;

    @Autowired
    ItemReader<Integer> step3ItemReader;

    @Autowired
    ItemReader<Integer> step4ItemReader;

    @Autowired
    ItemReader<Integer> step5ItemReader;

    @Autowired
    ItemProcessor<Integer,BatchData> stepItemProcessor;

    @Autowired
    ItemWriter<BatchData> step1ItemWriter;

    @Autowired
    Partitioner stepPartitioner;

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    StepListener stepListener;

    @Autowired
    ItemProcessListener<Integer,BatchData> step1ItemProcessListener;


    @Autowired
    StepItemWriterListener stepItemWriterListener;

    @Bean
    public Step step1(){

        return steps.get("step1").<Integer, BatchData>chunk(20)
                .reader(step1ItemReader)
                .processor(stepItemProcessor)
                .writer(step1ItemWriter)
                .listener(stepListener)
                .listener(stepItemWriterListener)
                .listener(step1ItemProcessListener)
                .build();
    }

    @Bean
    public Step partitionStep1(){

        return steps.get("partitionStep1")
                .partitioner("partitionStep1", stepPartitioner)
                .step(step1())
                .gridSize(5)
                .taskExecutor(threadPoolTaskExecutor)
                .build();
    }

    @Bean
    public Step step2(){

        return steps.get("step2").<Integer, BatchData>chunk(20)
                .reader(step2ItemReader)
                .processor(stepItemProcessor)
                .writer(step1ItemWriter)
                .listener(stepListener)
                .listener(stepItemWriterListener)
                .listener(step1ItemProcessListener)
                .build();
    }

    @Bean
    public Step partitionStep2(){

        return steps.get("partitionStep2")
                .partitioner("partitionStep1", stepPartitioner)
                .step(step2())
                .gridSize(5)
                .taskExecutor(threadPoolTaskExecutor)
                .build();
    }

    @Bean
    public Step step3(){

        return steps.get("step3").<Integer, BatchData>chunk(20)
                .reader(step3ItemReader)
                .processor(stepItemProcessor)
                .writer(step1ItemWriter)
                .listener(stepListener)
                .listener(stepItemWriterListener)
                .listener(step1ItemProcessListener)
                .build();
    }

    @Bean
    public Step partitionStep3(){

        String stepName = "partitionStep3";
        return steps.get(stepName)
                .partitioner(stepName, stepPartitioner)
                .step(step3())
                .gridSize(5)
                .taskExecutor(threadPoolTaskExecutor)
                .build();
    }

    @Bean
    public Step step4(){

        return steps.get("step4").<Integer, BatchData>chunk(20)
                .reader(step4ItemReader)
                .processor(stepItemProcessor)
                .writer(step1ItemWriter)
                .listener(stepListener)
                .listener(stepItemWriterListener)
                .listener(step1ItemProcessListener)
                .build();
    }

    @Bean
    public Step partitionStep4(){

        String stepName = "partitionStep4";
        return steps.get(stepName)
                .partitioner(stepName, stepPartitioner)
                .step(step4())
                .gridSize(5)
                .taskExecutor(threadPoolTaskExecutor)
                .build();
    }

    @Bean
    public Step step5(){

        return steps.get("step5").<Integer, BatchData>chunk(20)
                .reader(step5ItemReader)
                .processor(stepItemProcessor)
                .writer(step1ItemWriter)
                .listener(stepListener)
                .listener(stepItemWriterListener)
                .listener(step1ItemProcessListener)
                .build();
    }

    @Bean
    public Step partitionStep5(){

        String stepName = "partitionStep5";
        return steps.get(stepName)
                .partitioner(stepName, stepPartitioner)
                .step(step5())
                .gridSize(5)
                .taskExecutor(threadPoolTaskExecutor)
                .build();
    }
}
