package couponManagement.manager;

import couponManagement.model.*;
import couponManagement.storage.BatchStorage;
import couponManagement.storage.CouponStorage;

import java.util.Date;
import java.util.List;

public class CouponManager {
    private CouponStorage couponStorage;
    private BatchStorage batchStorage;

    public CouponManager() {
        couponStorage = new CouponStorage();
        batchStorage = BatchStorage.getInstance();
    }

    public Coupon grantCoupon(String batchId) {
        Batch batch = batchStorage.getBatch(batchId);
        System.out.printf(batch.toString());
        if(!canGrantCoupon(batch)) {
            throw new IllegalArgumentException("CannotGrantCoupon");
        }
        Coupon coupon;
        if(batch instanceof OpenBatch) {
            OpenBatch openBatch = (OpenBatch) batch;
            coupon = Coupon.builder()
                    .couponId(openBatch.getCouponCode())
                    .isExpired(false)
                    .build();
            openBatch.setUsedGrants(openBatch.getUsedGrants() + 1);
            batchStorage.updateBatch(openBatch);
            couponStorage.setCoupon(coupon);
        } else if (batch instanceof  ClosedBatch) {
            ClosedBatch closedBatch = (ClosedBatch) batch;
            List<String> availableCouponCodes = closedBatch.getAvailableCoupons();
            List<String> grantedCoupons = closedBatch.getGrantedCoupons();
            String couponCode = availableCouponCodes.get(0);
            availableCouponCodes.remove(couponCode);
            grantedCoupons.add(couponCode);
            coupon = Coupon.builder()
                    .couponId(couponCode)
                    .isExpired(false)
                    .build();
            closedBatch.setGrantedCoupons(grantedCoupons);
            closedBatch.setAvailableCoupons(availableCouponCodes);
            batchStorage.addBatch(closedBatch);
            couponStorage.setCoupon(coupon);
        } else {
            throw new IllegalArgumentException("InvalidBatchId");
        }
        return coupon;
    }

    public Coupon getCoupon(String couponId) {
        return couponStorage.getCoupon(couponId);
    }

    public int getCouponsCount(String batchId) {
        Batch batch = batchStorage.getBatch(batchId);
        int count = 0;
        if(batch instanceof OpenBatch) {
            OpenBatch openBatch = (OpenBatch) batch;
            return (openBatch.getMaxAllowedGrants() - openBatch.getUsedGrants());
        } else if (batch instanceof  ClosedBatch) {
            ClosedBatch closedBatch = (ClosedBatch) batch;
            return closedBatch.getAvailableCoupons().size();
        }
        throw new IllegalArgumentException("InvalidBatchId");
    }

    private boolean canGrantCoupon(Batch batch) {
        System.out.println("Coming  here");
        Date date = new Date();
        if(date.getTime() < batch.getValidFromTimestamp() || date.getTime() > batch.getValidTillTimestamp()) {
            System.out.println("Invalid timestamp");
            return false;
        }

        if(batch.getBatchState() != BatchState.ACTIVE) {
            System.out.println("Invalid state");
            return false;
        }

        if(batch instanceof OpenBatch) {
            OpenBatch openBatch = (OpenBatch) batch;
            if(openBatch.getUsedGrants() >= openBatch.getMaxAllowedGrants()) {
                System.out.println("Used grants more than max allowed grants");
                return false;
            }
        } else if (batch instanceof ClosedBatch) {
            ClosedBatch closedBatch = (ClosedBatch) batch;
            if(closedBatch.getAvailableCoupons().isEmpty()) {
                System.out.println("Closed batch - no available coupons");
                return false;
            }
        }

        return true;
    }
}
