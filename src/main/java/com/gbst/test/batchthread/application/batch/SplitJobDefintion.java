package com.gbst.test.batchthread.application.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.FlowJobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Random;

import static java.lang.Math.abs;


@Component
public class SplitJobDefintion {

    Logger logger = LoggerFactory.getLogger(SplitJobDefintion.class);

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    private Tasklet getTaskLet(String name){
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                Random rnd = new Random(Calendar.getInstance().getTimeInMillis());

                for(int i= 0; i<100; i++){
                    //Thread.sleep( abs(rnd.nextLong() * 200));
                    logger.debug("tasklet" + name);
                };
                return null;
            }
        };
    }

    private Step getStep(String name){
        return steps.get("step" + name)
                .tasklet(getTaskLet(name))
                .build();
    }

    private Flow getFlow(String name){
        return new FlowBuilder<Flow>("flow" + name)
                .start(getStep(name  ))
                .next(getStep(name + "next1"))
                .build();
    }

    @Bean("splitJob")
    public Job splitJob(){
        Flow flow = new FlowBuilder<Flow>("splitJob")
                .split(threadPoolTaskExecutor)
                .add(getFlow("1"))
                .split(threadPoolTaskExecutor)
                .add(getFlow("2"))
                .build();

        return jobs.get("splitJob")
                .start(flow)
                .end()
                .build();
    }
}
