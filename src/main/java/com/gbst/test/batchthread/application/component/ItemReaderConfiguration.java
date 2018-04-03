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

@Component
public class ItemReaderConfiguration {

    @Autowired
    BatchDataRepository batchDataRepository;
    
    @StepScope
    @Bean
    public ItemReader<Integer> step2ItemReader(@Value("#{stepExecution}") StepExecution stepExecution) {

        String prevStep="step1";
        String curStep="step2";

        stepExecution.getExecutionContext().putString("curStep", curStep);
        List<String> arguments = new ArrayList<String>();
        arguments.add(prevStep);
        arguments.add(curStep);

        Map<String, Sort.Direction> sorting = new HashMap<>();
        sorting.put("batchDataId", Sort.Direction.ASC);

        RepositoryItemReader<Integer> reader = new RepositoryItemReader<>();
        reader.setRepository(batchDataRepository);
        reader.setMethodName("findAllForReader");
        reader.setArguments(arguments);
        reader.setSort(sorting);
        reader.setPageSize(50);
        reader.setSaveState(false);
        return reader;
    }

    @StepScope
    @Bean
    public ItemReader<Integer> step3ItemReader(@Value("#{stepExecution}") StepExecution stepExecution) {

        String prevStep="step2";
        String curStep="step3";

        stepExecution.getExecutionContext().putString("curStep", curStep);
        List<String> arguments = new ArrayList<String>();
        arguments.add(prevStep);
        arguments.add(curStep);

        Map<String, Sort.Direction> sorting = new HashMap<>();
        sorting.put("batchDataId", Sort.Direction.ASC);

        RepositoryItemReader<Integer> reader = new RepositoryItemReader<>();
        reader.setRepository(batchDataRepository);
        reader.setMethodName("findAllForReader");
        reader.setArguments(arguments);
        reader.setSort(sorting);
        reader.setPageSize(50);
        reader.setSaveState(false);
        return reader;
    }

    @StepScope
    @Bean
    public ItemReader<Integer> step4ItemReader(@Value("#{stepExecution}") StepExecution stepExecution) {

        String prevStep="step3";
        String curStep="step4";

        stepExecution.getExecutionContext().putString("curStep", curStep);
        List<String> arguments = new ArrayList<String>();
        arguments.add(prevStep);
        arguments.add(curStep);

        Map<String, Sort.Direction> sorting = new HashMap<>();
        sorting.put("batchDataId", Sort.Direction.ASC);

        RepositoryItemReader<Integer> reader = new RepositoryItemReader<>();
        reader.setRepository(batchDataRepository);
        reader.setMethodName("findAllForReader");
        reader.setArguments(arguments);
        reader.setSort(sorting);
        reader.setPageSize(50);
        reader.setSaveState(false);
        return reader;
    }

    @StepScope
    @Bean
    public ItemReader<Integer> step5ItemReader(@Value("#{stepExecution}") StepExecution stepExecution) {

        String prevStep="step4";
        String curStep="step5";

        stepExecution.getExecutionContext().putString("curStep", curStep);
        List<String> arguments = new ArrayList<String>();
        arguments.add(prevStep);
        arguments.add(curStep);

        Map<String, Sort.Direction> sorting = new HashMap<>();
        sorting.put("batchDataId", Sort.Direction.ASC);

        RepositoryItemReader<Integer> reader = new RepositoryItemReader<>();
        reader.setRepository(batchDataRepository);
        reader.setMethodName("findAllForReader");
        reader.setArguments(arguments);
        reader.setSort(sorting);
        reader.setPageSize(50);
        reader.setSaveState(false);
        return reader;
    }
}
