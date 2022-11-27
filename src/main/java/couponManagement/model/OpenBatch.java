package couponManagement.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class OpenBatch extends Batch {
    private int maxAllowedGrants;
    private int usedGrants;
    private String couponCode;
}
