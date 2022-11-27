package couponManagement.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class ClosedBatch extends Batch {
    private List<String> availableCoupons;
    private List<String> grantedCoupons;
}
