package com.fgiaquinta.katas.streams.reduction;

import com.fgiaquinta.katas.domain.Charter;

import java.util.List;

/**
 * Exercise C06 — Top 3 Aircraft By Revenue.
 *
 * <h2>Statement</h2>
 * Given a list of {@link Charter}, group them by aircraft registration, sum the {@code totalPrice} of each
 * aircraft's charters, and return the registrations of the three aircraft with the highest summed revenue,
 * ordered from highest to lowest.
 *
 * <h2>Concepts</h2>
 * grouping by registration with a summing downstream, then {@code sorted} and {@code limit}; {@code sorted} buffers and sorts everything before {@code limit}, which is otherwise short-circuiting; bad to parallelize at small sizes.
 *
 * <h2>Internals</h2>
 * TODO after solving: explain what happens inside the pipeline, not what the code does.
 * <h2>Anchor</h2>
 * TODO after solving: one sentence, your own image from a world you know, that ties this operation to its mechanism.
 */
public final class C06Top3AircraftByRevenue {

    private C06Top3AircraftByRevenue() {
    }

    public static List<String> top3RegistrationsByRevenue(List<Charter> charters) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
