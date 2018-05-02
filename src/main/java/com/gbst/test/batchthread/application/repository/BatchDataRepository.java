package com.gbst.test.batchthread.application.repository;

import com.gbst.test.batchthread.application.model.BatchData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface BatchDataRepository extends JpaRepository<BatchData, Integer> {

    @Query(value = "select b.batchDataId from BatchData b where " +
            "b.lastStep = :lastStep and b.steps like '%'+ :step +'%' and b.batchDataId % convert(int,:max) = convert(int,:segment)")
    Page<Integer> findAllItemReader(@Param("lastStep") String lastStep,
                                @Param("step") String step,
                                @Param("max") String max,
                                @Param("segment") String segment,
                                 Pageable pageable);

    @Query(value = "select b.batchDataId from BatchData b where " +
            " b.jobCreationId = convert(int,:jobExecutionId) " +
            " and b.lastStep < :step and b.nextStep <> :step and b.steps like '%'+ :step +'%' "+
            " and b.batchDataId % convert(int,:max) = convert(int,:segment)")
    Page<Integer> findAllForReader(@Param("step") String step,
                                   @Param("jobExecutionId") String jobExecutionId,
                                   @Param("max") String max,
                                   @Param("segment") String segment,
                                   Pageable pageable);

    @Query(value = "select b.batchDataId from BatchData b where " +
            " b.jobCreationId = convert(int,:jobExecutionId) " +
            " and b.lastStep < :step and b.nextStep <> :step and b.steps like '%'+ :step +'%' ")
    Page<Integer> findAllForAsyncReader(@Param("step") String step,
                                   @Param("jobExecutionId") String jobExecutionId,
                                   Pageable pageable);


    @Query(value = "select b.batchDataId from BatchData b where b.lastStep=:lastStep")
    Page<Integer> findAllId(@Param("lastStep") String lastStep, Pageable pageable);

    @Modifying
    @Query(value = "update BatchData b set b.lastStep = b.nextStep ")
    void updateLastStep();
}
