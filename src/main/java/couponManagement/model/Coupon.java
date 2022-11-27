package couponManagement.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Coupon {
    private String couponId;
    private boolean isExpired;
}
