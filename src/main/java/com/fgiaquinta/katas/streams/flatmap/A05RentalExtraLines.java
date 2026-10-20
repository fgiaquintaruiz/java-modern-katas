package com.fgiaquinta.katas.streams.flatmap;

import com.fgiaquinta.katas.domain.Rental;

import java.util.List;

/**
 * Exercise A05 — Rental extra lines.
 *
 * <h2>Statement</h2>
 * Given a list of {@link Rental}, produce one formatted line per extra attached to each rental,
 * using the format {@code "<rentalId>:<extraName>"}. A rental with no extras contributes no lines
 * at all.
 *
 * <h2>Concepts</h2>
 * {@code flatMap} yielding variable-size inner streams, including {@code Stream.empty()}; one inner {@code Stream} allocated per outer element; why {@code map} plus {@code filter} cannot express a one-to-many expansion.
 *
 * <h2>Internals</h2>
 * TODO after solving: explain what happens inside the pipeline, not what the code does.
 * <h2>Anchor</h2>
 * TODO after solving: one sentence, your own image from a world you know, that ties this operation to its mechanism.
 */
public final class A05RentalExtraLines {

    private A05RentalExtraLines() {
    }

    public static List<String> extraLines(List<Rental> rentals) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
