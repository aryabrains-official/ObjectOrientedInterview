package couponManagement.utils;

import java.util.UUID;

public class BatchIdGenerator {

    public static String generate() {
        return UUID.randomUUID().toString();
    }
}
