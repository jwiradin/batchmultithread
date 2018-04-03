package com.gbst.test.batchthread.application.component;

import com.gbst.test.batchthread.application.model.BatchData;
import org.springframework.batch.core.ItemProcessListener;

public class StepItemProcessorListener implements ItemProcessListener<Integer,BatchData> {
    @Override
    public void beforeProcess(Integer integer) {

    }

    @Override
    public void afterProcess(Integer integer, BatchData batchData) {

    }

    @Override
    public void onProcessError(Integer integer, Exception e) {

    }
}
