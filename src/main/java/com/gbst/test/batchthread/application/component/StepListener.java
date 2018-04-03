package com.gbst.test.batchthread.application.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class StepListener implements StepExecutionListener {

    private final Logger logger = LoggerFactory.getLogger(StepListener.class);

    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.debug("Step name {} exit id {} status{}", stepExecution.getStepName(), stepExecution.getId(), stepExecution.getExitStatus().getExitCode());
        return null;
    }
}
