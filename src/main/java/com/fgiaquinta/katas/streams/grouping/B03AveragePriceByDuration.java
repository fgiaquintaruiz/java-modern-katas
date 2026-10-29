package com.fgiaquinta.katas.streams.grouping;

import com.fgiaquinta.katas.domain.Charter;

import java.util.List;
import java.util.Map;

/**
 * Exercise B03 — Average price of long vs. short charters.
 *
 * <h2>Statement</h2>
 * Given a list of {@link Charter} and a minimum number of days {@code minDays}, split charters into
 * "long" (key {@code true}, {@code days() >= minDays}) and "short" (key {@code false}) groups.
 * Return a {@link Map} from {@link Boolean} to the average {@code totalPrice} of each group, as a
 * {@code double}.
 *
 * <h2>Concepts</h2>
 * {@code partitioningBy} always materialises both {@code true}/{@code false} keys, unlike {@code groupingBy}; {@code averagingDouble}'s internal two-slot {@code double[]} accumulator; precision loss converting {@code BigDecimal} to {@code double}.
 *
 * <h2>Internals</h2>
 * TODO after solving: explain what happens inside the pipeline, not what the code does.
 * <h2>Anchor</h2>
 * TODO after solving: one sentence, your own image from a world you know, that ties this operation to its mechanism.
 */
public final class B03AveragePriceByDuration {

    private B03AveragePriceByDuration() {
    }

    public static Map<Boolean, Double> averagePriceByLongCharter(List<Charter> charters, int minDays) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
