package com.gbst.test.batchthread.application.component;

import com.gbst.test.batchthread.application.repository.BatchDataRepository;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by juliusl on 2/05/2018.
 */
@Component
public class AsyncItemReaderComponent {
    @Autowired
    BatchDataRepository batchDataRepository;


    @StepScope
    @Bean
    public ItemReader<Integer> asyncStep1ItemReader(@Value("#{stepExecution}") StepExecution stepExecution) {
        String curStep="step1";
        return getItemReader(curStep, stepExecution);
    }


    @StepScope
    @Bean
    public ItemReader<Integer> asyncStep2ItemReader(@Value("#{stepExecution}") StepExecution stepExecution) {

        String curStep="step2";
        return getItemReader(curStep, stepExecution);
    }

    @StepScope
    @Bean
    public ItemReader<Integer> asyncStep3ItemReader(@Value("#{stepExecution}") StepExecution stepExecution) {

        String curStep="step3";
        return getItemReader(curStep, stepExecution);

    }

    @StepScope
    @Bean
    public ItemReader<Integer> asyncStep4ItemReader(@Value("#{stepExecution}") StepExecution stepExecution) {

        String curStep="step4";
        return getItemReader(curStep, stepExecution);

    }

    @StepScope
    @Bean
    public ItemReader<Integer> asyncStep5ItemReader(@Value("#{stepExecution}") StepExecution stepExecution) {

        String curStep="step5";
        return getItemReader(curStep, stepExecution);

    }

    private ItemReader<Integer> getItemReader(String curStep, StepExecution stepExecution){
        stepExecution.getExecutionContext().putString("curStep", curStep);

        List<String> arguments = new ArrayList<String>();

        arguments.add(curStep);
        arguments.add(stepExecution.getJobParameters().getLong("jobRequestId").toString());


        Map<String, Sort.Direction> sorting = new HashMap<>();
        sorting.put("batchDataId", Sort.Direction.ASC);

        RepositoryItemReader<Integer> reader = new RepositoryItemReader<>();
        reader.setRepository(batchDataRepository);
        reader.setMethodName("findAllForAsyncReader");
        reader.setArguments(arguments);
        reader.setSort(sorting);
        reader.setPageSize(5);
        reader.setSaveState(false);

        return reader;
    }
}
