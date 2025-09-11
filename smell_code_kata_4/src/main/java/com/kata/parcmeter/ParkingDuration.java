package com.kata.parcmeter;

import com.kata.parcmeter.exception.InvalidTimeRangeException;
import com.kata.parcmeter.log.ConsoleLogger;
import com.kata.parcmeter.log.Logger;

import java.time.Duration;
import java.time.LocalDateTime;

public class ParkingDuration {
    private static final int FREE_MINUTES = 15;
    private static final int MINUTES_PER_HOUR = 60;

    private static final Logger logger = new ConsoleLogger();

    private final long totalMinutes;

    private ParkingDuration(long minutes) {
        this.totalMinutes = Math.max(0, minutes);
    }

    public static ParkingDuration between(LocalDateTime start, LocalDateTime end) {
        if (start == null && end == null) {
            logger.warn("Both start and end times are null, returning zero duration");
            return new ParkingDuration(0);
        }

        if (start == null) {
            throw new InvalidTimeRangeException("Start time cannot be null");
        }

        if (end == null) {
            throw new InvalidTimeRangeException("End time cannot be null");
        }

        if (end.isBefore(start)) {
            throw new InvalidTimeRangeException(
                    String.format("End time (%s) cannot be before start time (%s)", end, start));
        }

        try {
            long minutes = Duration.between(start, end).toMinutes();
            logger.debug(String.format("Calculated parking duration: %d minutes (%s to %s)",
                    minutes, start, end));
            return new ParkingDuration(minutes);
        } catch (Exception e) {
            logger.error("Failed to calculate duration between " + start + " and " + end, e);
            throw new InvalidTimeRangeException("Unable to calculate parking duration");
        }
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
