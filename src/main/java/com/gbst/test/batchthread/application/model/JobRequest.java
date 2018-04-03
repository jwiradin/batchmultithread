package com.gbst.test.batchthread.application.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class JobRequest {
    @Id
    @SequenceGenerator(name = "hibSequence", allocationSize = 20, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibSequence")
    private Long jobRequestId;

    @Column(name = "createDate")
    private LocalDateTime createDate;

    @Column(name = "startDate")
    private LocalDateTime startDate;

    @Column(name = "completeDate")
    private LocalDateTime completeDate;

    private String status;

    private Long jobExecutionId;

    public Long getJobRequestId() {
        return jobRequestId;
    }

    public void setJobRequestId(Long jobRequestId) {
        this.jobRequestId = jobRequestId;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(LocalDateTime completeDate) {
        this.completeDate = completeDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getJobExecutionId() {
        return jobExecutionId;
    }

    public void setJobExecutionId(Long jobExecutionId) {
        this.jobExecutionId = jobExecutionId;
    }
    
}
