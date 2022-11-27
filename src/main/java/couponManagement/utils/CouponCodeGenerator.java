package couponManagement.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CouponCodeGenerator {
    public static String getSingleCode() {
        return UUID.randomUUID().toString();
    }

    public static List<String> getCouponCodeList(int numCoupons) {
        List<String> couponCodeList = new ArrayList<>();
        for(int i=0; i < numCoupons; i++) {
            couponCodeList.add(UUID.randomUUID().toString());
        }
        return couponCodeList;
    }
}
