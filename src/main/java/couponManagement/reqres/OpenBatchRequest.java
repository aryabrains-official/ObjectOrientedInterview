package couponManagement.reqres;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OpenBatchRequest {
    private String distributor;
    private Long validFromTimestamp;
    private Long validTillTimestamp;
    private int maxAllowedGrants;
}
