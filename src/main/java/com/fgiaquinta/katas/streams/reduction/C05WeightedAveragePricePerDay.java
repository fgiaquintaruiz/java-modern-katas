package com.fgiaquinta.katas.streams.reduction;

import com.fgiaquinta.katas.domain.Rental;

import java.math.BigDecimal;
import java.util.List;

/**
 * Exercise C05 — Weighted Average Price Per Day.
 *
 * <h2>Statement</h2>
 * Given a list of {@link Rental}, compute the sum of all {@code totalPrice} divided by the sum of
 * all {@code days()}, rounded to scale {@code 2} with {@link java.math.RoundingMode#HALF_UP}. The
 * computation MUST use the three-argument {@code reduce(identity, accumulator, combiner)} over the
 * immutable accumulator record {@code Totals(BigDecimal price, long days)} nested in this class, in
 * a single pass.
 *
 * <h2>Concepts</h2>
 * the three-argument {@code reduce}, needed when the accumulator type differs from the element type; the combiner must be associative for parallel correctness; a per-element record costs more than mutable {@code collect(supplier, accumulator, combiner)}.
 *
 * <h2>Internals</h2>
 * TODO after solving: explain what happens inside the pipeline, not what the code does.
 * <h2>Anchor</h2>
 * TODO after solving: one sentence, your own image from a world you know, that ties this operation to its mechanism.
 */
public final class C05WeightedAveragePricePerDay {

    private C05WeightedAveragePricePerDay() {
    }

    /**
     * Immutable partial accumulator used by the three-argument
     * {@code reduce} to track the running total price and the running
     * total number of days in a single pass.
     */
    public record Totals(BigDecimal price, long days) {
    }

    public static BigDecimal weightedAveragePricePerDay(List<Rental> rentals) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
