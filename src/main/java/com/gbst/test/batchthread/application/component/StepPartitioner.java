package com.gbst.test.batchthread.application.component;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StepPartitioner implements Partitioner {
    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {

        Map<String, ExecutionContext> result = new HashMap<String, ExecutionContext>();

        for(int i = 0; i < gridSize; i++){
            ExecutionContext val = new ExecutionContext();

            val.putInt("segment", i);
            val.putInt("max", gridSize);

            result.put("partition" + i, val);
        }

        return result;
    }
}