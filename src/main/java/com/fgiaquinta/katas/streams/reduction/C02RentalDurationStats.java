package com.fgiaquinta.katas.streams.reduction;

import com.fgiaquinta.katas.domain.Rental;

import java.util.List;
import java.util.LongSummaryStatistics;

/**
 * Exercise C02 — Rental Duration Stats.
 *
 * <h2>Statement</h2>
 * Given a list of {@link Rental}, compute summary statistics (count, sum, min, average, max) of
 * {@code days()} across all rentals in a single pass, returned as a {@link LongSummaryStatistics}.
 *
 * <h2>Concepts</h2>
 * {@code summaryStatistics()} as a single-pass mutable reduction; empty-input sentinel values explain why min/max aren't {@code 0}; {@code LongSummaryStatistics} as a mutable container merged via {@code combine()} in parallel.
 *
 * <h2>Internals</h2>
 * TODO after solving: explain what happens inside the pipeline, not what the code does.
 * <h2>Anchor</h2>
 * TODO after solving: one sentence, your own image from a world you know, that ties this operation to its mechanism.
 */
public final class C02RentalDurationStats {

    private C02RentalDurationStats() {
    }

    public static LongSummaryStatistics durationStats(List<Rental> rentals) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
