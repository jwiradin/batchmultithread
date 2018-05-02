package com.gbst.test.batchthread.application.component;

import com.gbst.test.batchthread.application.model.BatchData;
import com.gbst.test.batchthread.application.model.JobRequest;
import com.gbst.test.batchthread.application.repository.BatchDataRepository;
import com.gbst.test.batchthread.application.repository.JobRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Component
public class DefaultRunner implements CommandLineRunner {

    @Autowired
    Job batchJob;

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    BatchDataRepository batchDataRepository;

    @Autowired
    @Qualifier("splitJob")
    Job splitJob;

    @Autowired
    JobRequestRepository jobRequestRepository;

    Logger logger = LoggerFactory.getLogger(DefaultRunner.class);
    
    @Override
    public void run(String... args) throws Exception {

        JobRequest jobRequest = new JobRequest();
        jobRequest.setCreateDate(LocalDateTime.now());
        jobRequest.setStatus("");
        jobRequestRepository.save(jobRequest);

            /*
        Map<String, JobParameter> parameters = new HashMap<>();

        parameters.put("startTime", new JobParameter(Calendar.getInstance().getTimeInMillis()) );

        jobLauncher.run(splitJob, new JobParameters(parameters));
                  */
    }
}
