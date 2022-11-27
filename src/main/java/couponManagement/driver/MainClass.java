package couponManagement.driver;

import couponManagement.manager.BatchManager;
import couponManagement.manager.CouponManager;
import couponManagement.model.BatchState;
import couponManagement.model.Coupon;
import couponManagement.reqres.OpenBatchRequest;
import org.junit.jupiter.api.Assertions;

import java.util.Calendar;
import java.util.Date;

public class MainClass {

    public static void main(String[] args) {

        BatchManager bm = new BatchManager();
        CouponManager cm = new CouponManager();

        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.YEAR, 1);
        Date validTillDate = calendar.getTime();
        //create batch manager
        String batchId = bm.createOpenBatch(OpenBatchRequest.builder()
                        .distributor("distributor1")
                        .validFromTimestamp(currentDate.getTime())
                        .validTillTimestamp(validTillDate.getTime())
                        .maxAllowedGrants(100)
                        .build());

        // update state to active
        bm.updateState(batchId, BatchState.APPROVED);
        bm.updateState(batchId, BatchState.ACTIVE);

        // grant Coupon
        Coupon newCoupon = cm.grantCoupon(batchId);

        Coupon sameCoupon = cm.getCoupon(newCoupon.getCouponId());

        Assertions.assertEquals(newCoupon.getCouponId(), sameCoupon.getCouponId());
    }
}
