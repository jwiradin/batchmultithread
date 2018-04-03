package com.gbst.test.batchthread.application.component;

import com.gbst.test.batchthread.application.model.BatchData;
import com.gbst.test.batchthread.application.repository.BatchDataRepository;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@StepScope
public class StepItemProcessor implements ItemProcessor<Integer, BatchData> {

    @Value("#{stepExecution}")
    StepExecution stepExecution;

    @Autowired
    BatchDataRepository batchDataRepository;

    @Override
    public BatchData process(Integer batchDataId) throws Exception {
        BatchData output = batchDataRepository.findById(batchDataId).get();

        String curStep = stepExecution.getExecutionContext().getString("curStep");

        switch (curStep) {
            case "step1":
                output.setStep1(Thread.currentThread().getName());
                break;
            case "step2":
                output.setStep2(Thread.currentThread().getName());
                break;
            case "step3":
                output.setStep3(Thread.currentThread().getName());
                break;
            case "step4":
                output.setStep4(Thread.currentThread().getName());
                break;
            case "step5":
                output.setStep5(Thread.currentThread().getName());
                break;
        }
        output.setNextStep(curStep);

        return output;

    }
}
