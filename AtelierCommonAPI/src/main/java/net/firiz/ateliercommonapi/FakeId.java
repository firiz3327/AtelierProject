package net.firiz.ateliercommonapi;

import java.util.concurrent.atomic.AtomicInteger;

public final class FakeId {

    private FakeId() {
    }

    private static final int UNIQUE_RANGE = 1000;
    private static final int CHECK_VALUE = Integer.MIN_VALUE + UNIQUE_RANGE;
    private static final AtomicInteger id = new AtomicInteger();
    private static final AtomicInteger uniqueId = new AtomicInteger();

    public static int createId() {
        final int v = id.decrementAndGet();
        if (v <= CHECK_VALUE) {
            id.set(-1);
        }
        return v;
    }

    public static int createUniqueId() {
        final int v = uniqueId.incrementAndGet();
        if (v > UNIQUE_RANGE) {
            throw new IllegalStateException("The range of uniqueId (0 to 1000) has been exceeded.");
        }
        return CHECK_VALUE - v;
    }

}
