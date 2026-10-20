package com.fgiaquinta.katas.streams.reduction;

import com.fgiaquinta.katas.domain.Rental;

import java.math.BigDecimal;
import java.util.List;

/**
 * Exercise C03 — Total Revenue.
 *
 * <h2>Statement</h2>
 * Given a list of {@link Rental}, return the sum of their {@code totalPrice} as a {@link BigDecimal}.
 *
 * <h2>Concepts</h2>
 * {@code reduce(identity, accumulator)} as a two-argument fold; the identity laws needed for correctness in parallel; {@code BigDecimal}'s immutability makes it safe inside {@code reduce} without synchronisation.
 *
 * <h2>Internals</h2>
 * TODO after solving: explain what happens inside the pipeline, not what the code does.
 * <h2>Anchor</h2>
 * TODO after solving: one sentence, your own image from a world you know, that ties this operation to its mechanism.
 */
public final class C03TotalRevenue {

    private C03TotalRevenue() {
    }

    public static BigDecimal totalRevenue(List<Rental> rentals) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
