package com.gbst.test.batchthread.application.component;

import com.gbst.test.batchthread.application.model.BatchData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@StepScope
public class StepItemProcessorListener implements ItemProcessListener<Integer,BatchData> {

    private LocalDateTime start;
    private StepExecution stepExecution;
    private final Logger logger = LogManager.getLogger(StepItemProcessorListener.class);
    
    public StepItemProcessorListener(@Value("#{stepExecution}") StepExecution stepExecution)   {
        this.stepExecution = stepExecution;
    }
    
    @Override
    public void beforeProcess(Integer integer) {
        start = LocalDateTime.now();
    }

    @Override
    public void afterProcess(Integer integer, BatchData batchData) {

        logger.debug("AfterProcess {}", integer);
        batchData.setJobStart(start);
        batchData.setJobEnd(LocalDateTime.now());
        batchData.setJobExecutionId(stepExecution.getJobExecutionId());
    }

    @Override
    public void onProcessError(Integer integer, Exception e) {

    }

}
