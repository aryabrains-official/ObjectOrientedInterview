package couponManagement.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum BatchState {

    CREATED, APPROVED, ACTIVE, SUSPENDED, EXPIRED, TERMINATED;

    static {
        CREATED.validFromStates = Arrays.asList(new BatchState[]{});
        APPROVED.validFromStates = Arrays.asList(new BatchState[]{BatchState.CREATED});
        ACTIVE.validFromStates = Arrays.asList(new BatchState[]{BatchState.APPROVED, BatchState.SUSPENDED});
        SUSPENDED.validFromStates = Arrays.asList(new BatchState[]{BatchState.ACTIVE});
        EXPIRED.validFromStates = Arrays.asList(new BatchState[]{BatchState.ACTIVE, BatchState.SUSPENDED});
        TERMINATED.validFromStates = Arrays.asList(new BatchState[]{BatchState.ACTIVE,
                BatchState.SUSPENDED, BatchState.EXPIRED});
    }

    private List<BatchState> validFromStates = new ArrayList<>();

    public static boolean isValidStateChange(BatchState from, BatchState to) {
        return to.validFromStates.contains(from);
    }

}
