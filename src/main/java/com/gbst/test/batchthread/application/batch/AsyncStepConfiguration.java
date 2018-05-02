package com.gbst.test.batchthread.application.batch;

import com.gbst.test.batchthread.application.component.StepItemReaderListener;
import com.gbst.test.batchthread.application.component.StepItemWriterListener;
import com.gbst.test.batchthread.application.model.BatchData;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepListener;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * Created by juliusl on 2/05/2018.
 */
@Component
public class AsyncStepConfiguration {
    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    @Qualifier("asyncStep1ItemReader")
    ItemReader<Integer> asynchStep1ItemReader;

    @Autowired
    @Qualifier("asyncStep2ItemReader")
    ItemReader<Integer> asynchStep2ItemReader;

    @Autowired
    @Qualifier("asyncStep3ItemReader")
    ItemReader<Integer> asynchStep3ItemReader;

    @Autowired
    @Qualifier("asyncStep4ItemReader")
    ItemReader<Integer> asynchStep4ItemReader;

    @Autowired
    @Qualifier("asyncStep5ItemReader")
    ItemReader<Integer> asynchStep5ItemReader;

    @Autowired
    ItemProcessor<Integer,BatchData> stepItemProcessor;

    @Autowired
    ItemWriter<BatchData> step1ItemWriter;

    @Autowired
    ItemReadListener<Integer> stepItemReaderListener;

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    StepListener stepListener;

    @Autowired
    ItemProcessListener<Integer,BatchData> step1ItemProcessListener;


    @Autowired
    StepItemWriterListener stepItemWriterListener;

    @Bean
    public Step asyncStep1(){

        return steps.get("asynchStep1").<Integer, BatchData>chunk(50)
                .reader(asynchStep1ItemReader)
                .processor(stepItemProcessor)
                .writer(step1ItemWriter)
                .listener(stepListener)
                .listener(stepItemWriterListener)
                .listener(step1ItemProcessListener)
                .listener(stepItemReaderListener)
                .taskExecutor(threadPoolTaskExecutor)
                .build();
    }

    @Bean
    public Step asyncStep2(){

        return steps.get("asynchStep2").<Integer, BatchData>chunk(50)
                .reader(asynchStep2ItemReader)
                .processor(stepItemProcessor)
                .writer(step1ItemWriter)
                .listener(stepListener)
                .listener(stepItemWriterListener)
                .listener(step1ItemProcessListener)
                .listener(stepItemReaderListener)
                .taskExecutor(threadPoolTaskExecutor)
                .build();
    }

    @Bean
    public Step asyncStep3(){

        return steps.get("asynchStep3").<Integer, BatchData>chunk(50)
                .reader(asynchStep3ItemReader)
                .processor(stepItemProcessor)
                .writer(step1ItemWriter)
                .listener(stepListener)
                .listener(stepItemWriterListener)
                .listener(step1ItemProcessListener)
                .listener(stepItemReaderListener)
                .taskExecutor(threadPoolTaskExecutor)
                .build();
    }

    @Bean
    public Step asyncStep4(){

        return steps.get("asynchStep4").<Integer, BatchData>chunk(50)
                .reader(asynchStep4ItemReader)
                .processor(stepItemProcessor)
                .writer(step1ItemWriter)
                .listener(stepListener)
                .listener(stepItemWriterListener)
                .listener(step1ItemProcessListener)
                .listener(stepItemReaderListener)
                .taskExecutor(threadPoolTaskExecutor)
                .build();
    }

    @Bean
    public Step asyncStep5(){

        return steps.get("asynchStep5").<Integer, BatchData>chunk(50)
                .reader(asynchStep5ItemReader)
                .processor(stepItemProcessor)
                .writer(step1ItemWriter)
                .listener(stepListener)
                .listener(stepItemWriterListener)
                .listener(stepItemReaderListener)
                .listener(step1ItemProcessListener)
                .taskExecutor(threadPoolTaskExecutor)
                .build();
    }
}
