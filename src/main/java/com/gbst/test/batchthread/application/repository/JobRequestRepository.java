package com.gbst.test.batchthread.application.repository;

import com.gbst.test.batchthread.application.model.JobRequest;
import org.springframework.data.repository.CrudRepository;

public interface JobRequestRepository extends CrudRepository<JobRequest, Long> {
    Iterable<JobRequest> findAllByStatusNotLike(String status);
}
