package com.gbst.test.batchthread.application.batch;


import com.gbst.test.batchthread.application.model.BatchData;
import com.gbst.test.batchthread.application.model.JobRequest;
import com.gbst.test.batchthread.application.repository.BatchDataRepository;
import com.gbst.test.batchthread.application.repository.JobRequestRepository;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by juliusl on 1/05/2018.
 */
@Component
public class CreateBatchData {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;


    @Autowired
    BatchDataRepository batchDataRepository;

    @Autowired
    JobRequestRepository jobRequestRepository;

    @Bean
    Job batchDataJob(){

        return jobs.get("batchDataJob")
                .start(createBatchDataStep())
                .build();
    }


    private Step createBatchDataStep(){
        return steps.get("create")
                .tasklet(getBatchDataTask())
                .build();
    }
    
    private Tasklet getBatchDataTask(){
        return new Tasklet() {

            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                Long jobRequestId = (Long)chunkContext.getStepContext().getJobParameters().get("jobRequestId");

                for(int i = 1; i <= 200;i++){
                    BatchData batchData = new BatchData();
                    batchData.setJobCreationId(jobRequestId);
                    batchData.setLastStep("");
                    batchData.setNextStep("");
                    batchData.setSteps("step1,step2,step3,step4,step5");
                    batchDataRepository.save(batchData);
                }

                JobRequest jobRequest  = jobRequestRepository.findById(jobRequestId).get();
                jobRequest.setStatus("Created");
                return null;
            }
        };
    }
}
