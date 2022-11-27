package couponManagement.manager;

import couponManagement.model.*;
import couponManagement.reqres.ClosedBatchRequest;
import couponManagement.reqres.OpenBatchRequest;
import couponManagement.storage.BatchStorage;
import couponManagement.utils.BatchIdGenerator;
import couponManagement.utils.CouponCodeGenerator;

import java.util.ArrayList;
import java.util.List;

public class BatchManager {
    BatchStorage batchStorage;

    public BatchManager() {
        batchStorage = BatchStorage.getInstance();
    }

    public String createOpenBatch(OpenBatchRequest openBatchRequest) {
        Batch batch = OpenBatch.builder()
                .batchId(BatchIdGenerator.generate())
                .batchState(BatchState.CREATED)
                .maxAllowedGrants(openBatchRequest.getMaxAllowedGrants())
                .usedGrants(0)
                .distributor(openBatchRequest.getDistributor())
                .validFromTimestamp(openBatchRequest.getValidFromTimestamp())
                .validTillTimestamp(openBatchRequest.getValidTillTimestamp())
                .couponCode(CouponCodeGenerator.getSingleCode())
                .build();
        batchStorage.addBatch(batch);
        return batch.getBatchId();
    }

    public String createClosedBatch(ClosedBatchRequest closedBatchRequest) {
        Batch batch = ClosedBatch.builder()
                .batchId(BatchIdGenerator.generate())
                .batchState(BatchState.CREATED)
                .distributor(closedBatchRequest.getDistributor())
                .validFromTimestamp(closedBatchRequest.getValidFromTimestamp())
                .validTillTimestamp(closedBatchRequest.getValidTillTimestamp())
                .availableCoupons(null)
                .grantedCoupons(null)
                .build();
        batchStorage.addBatch(batch);
        return batch.getBatchId();
    }

    public OpenBatch getOpenBatch(String batchId) {
        Batch batch = batchStorage.getBatch(batchId);
        if(batch instanceof OpenBatch) {
            return (OpenBatch) batch;
        }
        throw new IllegalArgumentException("InvalidBatchIdForBatchType");
    }

    public ClosedBatch getClosedBatch(String batchId) {
        Batch batch = batchStorage.getBatch(batchId);
        if(batch instanceof ClosedBatch) {
            return (ClosedBatch) batch;
        }
        throw new IllegalArgumentException("InvalidBatchIdForBatchType");
    }

    public void updateState(String batchId, BatchState toBatchState) {
        Batch batch = batchStorage.getBatch(batchId);
        if(!BatchState.isValidStateChange(batch.getBatchState(), toBatchState)) {
            throw new IllegalArgumentException("INVALID_TRANSITION");
        }
        batch.setBatchState(toBatchState);
        batchStorage.updateBatch(batch);
    }

    public void ingestCoupons(String batchId, List<String> couponCodes) {
        Batch batch = batchStorage.getBatch(batchId);
        if(!(batch instanceof ClosedBatch)) {
            throw new IllegalArgumentException("OperationNotSupportedForBatchType");
        }
        ClosedBatch closedBatch = (ClosedBatch) batch;
        closedBatch.setAvailableCoupons(couponCodes);
        closedBatch.setGrantedCoupons(new ArrayList<>());
        batchStorage.updateBatch(closedBatch);
    }
}
