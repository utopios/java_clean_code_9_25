package com.kata.parcmeter;

import java.time.Duration;
import java.time.LocalDateTime;

public class ParkingDuration {
    private static final int FREE_MINUTES = 15;
    private static final int MINUTES_PER_HOUR = 60;

    private final long totalMinutes;

    private ParkingDuration(long minutes) {
        this.totalMinutes = Math.max(0, minutes);
    }

    public static ParkingDuration between(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            return new ParkingDuration(0);
        }
        return new ParkingDuration(Duration.between(start, end).toMinutes());
    }

    public boolean isFree() {
        return totalMinutes <= FREE_MINUTES;
    }

    public int getBillableHours() {
        if (isFree()) return 0;
        long billableMinutes = totalMinutes - FREE_MINUTES;
        return (int) Math.ceil(billableMinutes / (double) MINUTES_PER_HOUR);
    }
}
