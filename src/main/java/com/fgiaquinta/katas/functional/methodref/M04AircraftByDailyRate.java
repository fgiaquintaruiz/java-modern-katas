package com.fgiaquinta.katas.functional.methodref;

import com.fgiaquinta.katas.domain.Aircraft;

import java.util.List;

/**
 * Exercise M04 — Aircraft by daily rate.
 *
 * <h2>Statement</h2>
 * Return the aircraft ordered by ascending daily rate, without modifying the input. An unbound
 * instance method reference passed to a {@code Comparator} factory is REQUIRED; a lambda is not accepted.
 *
 * <h2>Concepts</h2>
 * unbound instance method reference as a key extractor ({@code Comparator.comparing(Aircraft::dailyRate)}).
 *
 * <h2>Internals</h2>
 * TODO after solving: explain what happens inside the pipeline, not what the code does.
 * <h2>Anchor</h2>
 * TODO after solving: one sentence, your own image from a world you know, that ties this operation to its mechanism.
 */
public final class M04AircraftByDailyRate {

    private M04AircraftByDailyRate() {
    }

    public static List<Aircraft> sortedByDailyRate(List<Aircraft> aircraft) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
