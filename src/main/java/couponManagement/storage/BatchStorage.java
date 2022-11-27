package couponManagement.storage;

import couponManagement.model.Batch;
import couponManagement.model.BatchState;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

public class BatchStorage {
    private Map<String, Batch> batchMap;
    private static BatchStorage onlyInstance;
    private BatchStorage() {
        batchMap = new HashMap<>();
    }
    public static BatchStorage getInstance() {
        // Only instantiate the object when needed.
        if (onlyInstance == null) {
            onlyInstance = new BatchStorage();
        }
        return onlyInstance;
    }

    public Batch getBatch(@NonNull final String batchId) {
        System.out.println("Get Batch");
        batchMap.forEach((key,value) -> {
            System.out.println("Key: " + key);
            System.out.println("Value: " + value.toString());
        });
        if(!batchMap.containsKey(batchId)) {
            throw new IllegalArgumentException("InvalidBatchId");
        }
        return batchMap.get(batchId);
    }

    public void addBatch(@NonNull final Batch batch) {
        if(batchMap.containsKey(batch.getBatchId())) {
            throw new IllegalArgumentException("BatchAlreadyExists");
        }
        System.out.println("Adding  batch for key: " + batch.getBatchId());
        batchMap.put(batch.getBatchId(), batch);
    }

    public void updateBatch(@NonNull final Batch updatedBatch) {
        batchMap.put(updatedBatch.getBatchId(), updatedBatch);
    }
}
