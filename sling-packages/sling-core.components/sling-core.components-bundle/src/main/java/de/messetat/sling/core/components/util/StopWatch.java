package de.messetat.sling.core.components.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class StopWatch {

    public static final long NANOS_PER_SECOND = 1000 * 1000 * 1000;

    private long start = System.nanoTime();
    private long lastLog = start;

    public long elapsedNanoSeconds() {
        return System.nanoTime() - start;
    }

    protected static String format(long elapsedNanoSeconds) {
        long milliSeconds = TimeUnit.NANOSECONDS.toMillis(elapsedNanoSeconds);
        long elapsedSeconds = TimeUnit.NANOSECONDS.toSeconds(elapsedNanoSeconds);
        long elapsedMinutes = TimeUnit.NANOSECONDS.toMinutes(elapsedNanoSeconds);
        long elapsedHours = TimeUnit.NANOSECONDS.toHours(elapsedNanoSeconds);
        if (elapsedSeconds > 0) {
            milliSeconds = milliSeconds % 1000;
        }
        if (elapsedMinutes > 0) {
            elapsedSeconds = elapsedSeconds % 60;
        }
        if (elapsedHours > 0) {
            elapsedMinutes = elapsedMinutes % 60;
        }

        String returnValue = String.format("%02d:%02d:%02d.%03d", elapsedHours, elapsedMinutes, elapsedSeconds, milliSeconds);
        return returnValue;
    }

    public String reset() {
        String returnValue = toString();
        start = System.nanoTime();
        return returnValue;
    }

    @Override
    public String toString() {
        long elapsedNanoSeconds = elapsedNanoSeconds();
        return format(elapsedNanoSeconds);
    }
}
