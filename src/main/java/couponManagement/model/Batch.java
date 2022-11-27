package couponManagement.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Batch {
    private String batchId;
    private String distributor;
    private BatchState batchState;
    private Long validFromTimestamp;
    private Long validTillTimestamp;
}
