package com.gbst.test.batchthread.application.component;

import com.gbst.test.batchthread.application.model.BatchData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StepItemWriterListener implements ItemWriteListener<BatchData> {

    private final Logger logger = LoggerFactory.getLogger(StepItemWriterListener.class);

    @Override
    public void beforeWrite(List<? extends BatchData> list) {
    }

    @Override
    public void afterWrite(List<? extends BatchData> list) {
        StringBuilder sb = new StringBuilder();
        list.forEach(b -> {sb.append(b.getBatchDataId()); sb.append(",");});
        logger.debug("written {} {}", list.size(), sb.toString());
    }

    @Override
    public void onWriteError(Exception e, List<? extends BatchData> list) {

    }
}
