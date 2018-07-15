package com.gbst.test.batchthread.application.component;

import com.gbst.test.batchthread.application.repository.JobRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.SchedulingAwareRunnable;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.ScheduledMethodRunnable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


@Component
public class BatchRequestMonitor {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    JobRequestRepository jobRequestRepository;

    @Autowired
    Job batchJob;

    @Autowired
    Job asyncJob;

    @Autowired
    Job batchDataJob;
    
    private final Logger logger = LoggerFactory.getLogger(BatchRequestMonitor.class);

    @Scheduled(fixedRate = 3000)
    public void start(){
        logger.debug("Start monitor");
        Map<String, JobParameter> jobId = new HashMap<>();

        jobRequestRepository.findAllByStatusNotLike("COMPLETED").forEach( jobRequest -> {
            switch (jobRequest.getStatus()){
                case "":
                    jobId.put("jobRequestId", new JobParameter(jobRequest.getJobRequestId().longValue()));
                    jobId.put("startTime", new JobParameter(Calendar.getInstance().getTimeInMillis()));
                    try {
                        jobLauncher.run(batchDataJob, new JobParameters(jobId));
                    } catch (JobExecutionException e){
                        e.printStackTrace();
                    }

                    break;
                case "Created":
                    logger.debug("Not started {}", jobRequest.getJobRequestId());
                    jobId.put("jobRequestId", new JobParameter(jobRequest.getJobRequestId().longValue()));
                    jobId.put("startTime", new JobParameter(Calendar.getInstance().getTimeInMillis()));

                    try {
                        jobLauncher.run(asyncJob, new JobParameters(jobId));
                    } catch (JobExecutionException e){
                        e.printStackTrace();
                    }

                    break;
                case "FAILED":
                    //logger.debug("Failed {}", jobRequest.getJobRequestId());
                    break;
                default:
                    break;
            };
        });

        logger.debug("End monitor");
    }
}
