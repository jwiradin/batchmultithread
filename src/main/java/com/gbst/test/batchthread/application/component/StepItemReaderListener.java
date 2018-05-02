package com.gbst.test.batchthread.application.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

@Component
public class StepItemReaderListener implements ItemReadListener<Integer> {
    private final Logger logger = LoggerFactory.getLogger(StepItemWriterListener.class);

    @Override
    public void beforeRead() {

    }

    @Override
    public void afterRead(Integer integer) {
        //logger.debug("read {}", integer);
    }

    @Override
    public void onReadError(Exception e) {

    }
}
