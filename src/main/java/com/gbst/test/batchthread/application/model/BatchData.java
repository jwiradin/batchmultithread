package com.gbst.test.batchthread.application.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class BatchData {
    @Id
    @SequenceGenerator(name = "hibSequence", allocationSize = 20,initialValue = 1)
    @GeneratedValue(generator = "hibSequence", strategy = GenerationType.SEQUENCE)
    int batchDataId;

    Long jobExecutionId;

    Long jobCreationId;
    
    LocalDateTime jobStart;

    LocalDateTime jobEnd;

    @Column(length = 50)
    String step1;

    @Column(length = 50)
    String step2;

    @Column(length = 50)
    String step3;

    @Column(length = 50)
    String step4;

    @Column(length = 50)
    String step5;

    @Column(length = 500)
    String steps;

    @Column(length = 50)
    String lastStep;

    @Column(length = 50)
    String nextStep;
    
    public int getBatchDataId() {
        return batchDataId;
    }

    public void setBatchDataId(int batchDataId) {
        this.batchDataId = batchDataId;
    }

    public Long getJobExecutionId() {
        return jobExecutionId;
    }

    public void setJobExecutionId(Long jobExecutionId) {
        this.jobExecutionId = jobExecutionId;
    }

    public LocalDateTime getJobStart() {
        return jobStart;
    }

    public void setJobStart(LocalDateTime jobStart) {
        this.jobStart = jobStart;
    }

    public LocalDateTime getJobEnd() {
        return jobEnd;
    }

    public void setJobEnd(LocalDateTime jobEnd) {
        this.jobEnd = jobEnd;
    }

    public String getStep1() {
        return step1;
    }

    public void setStep1(String step1) {
        this.step1 = step1;
    }

    public String getStep2() {
        return step2;
    }

    public void setStep2(String step2) {
        this.step2 = step2;
    }

    public String getStep3() {
        return step3;
    }

    public void setStep3(String step3) {
        this.step3 = step3;
    }

    public String getStep4() {
        return step4;
    }

    public void setStep4(String step4) {
        this.step4 = step4;
    }

    public String getStep5() {
        return step5;
    }

    public void setStep5(String step5) {
        this.step5 = step5;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getLastStep() {
        return lastStep;
    }

    public void setLastStep(String lastStep) {
        this.lastStep = lastStep;
    }

    public String getNextStep() {
        return nextStep;
    }

    public void setNextStep(String nextStep) {
        this.nextStep = nextStep;
    }

    public Long getJobCreationId() {
        return jobCreationId;
    }

    public void setJobCreationId(Long jobCreationId) {
        this.jobCreationId = jobCreationId;
    }
}
