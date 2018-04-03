package com.gbst.test.batchthread.application.component;

import com.gbst.test.batchthread.application.model.JobRequest;
import com.gbst.test.batchthread.application.repository.JobRequestRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;

@Component
public class JobRequestExecutionListener implements JobExecutionListener {

    @Autowired
    JobRequestRepository jobRequestRepository;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        Long jobRequestId = jobExecution.getJobParameters().getLong("jobRequestId");

        JobRequest jobRequest = getJobRequest(jobRequestId);
        jobRequest.setJobExecutionId(jobExecution.getId());
        jobRequest.setStartDate(LocalDateTime.now());
        jobRequestRepository.save(jobRequest);

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        Long jobRequestId = jobExecution.getJobParameters().getLong("jobRequestId");

        JobRequest jobRequest = getJobRequest(jobRequestId);
        jobRequest.setCompleteDate(LocalDateTime.now());
        jobRequest.setStatus(jobExecution.getStatus().toString());
        jobRequestRepository.save(jobRequest);
    }
    
    JobRequest getJobRequest(Long jobRequestId){
        JobRequest result =jobRequestRepository.findById(jobRequestId).get();

        if (result == null){
            throw new DataRetrievalFailureException(String.format("Invalid JobRequest Id %d.", jobRequestId));
        }

        return result;
    }
}
