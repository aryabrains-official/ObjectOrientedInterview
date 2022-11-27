package couponManagement.storage;

import couponManagement.model.Coupon;

import java.util.HashMap;
import java.util.Map;

public class CouponStorage {
    Map<String, Coupon> couponCodeToCouponMap;

    public CouponStorage() {
        couponCodeToCouponMap = new HashMap<>();
    }

    public Coupon getCoupon(String couponCode)  {
        return couponCodeToCouponMap.get(couponCode);
    }

    public void setCoupon(Coupon coupon) {
        couponCodeToCouponMap.put(coupon.getCouponId(), coupon);
    }
}
