package com.fgiaquinta.katas.streams.reduction;

import com.fgiaquinta.katas.domain.Rental;

import java.util.List;

/**
 * Exercise C06 — Top 3 Vehicles By Revenue.
 *
 * <h2>Statement</h2>
 * Given a list of {@link Rental}, group them by vehicle plate, sum the {@code totalPrice} of each
 * vehicle's rentals, and return the plates of the three vehicles with the highest summed revenue,
 * ordered from highest to lowest.
 *
 * <h2>Concepts</h2>
 * grouping by plate with a summing downstream, then {@code sorted} and {@code limit}; {@code sorted} buffers and sorts everything before {@code limit}, which is otherwise short-circuiting; bad to parallelize at small sizes.
 *
 * <h2>Internals</h2>
 * TODO after solving: explain what happens inside the pipeline, not what the code does.
 * <h2>Anchor</h2>
 * TODO after solving: one sentence, your own image from a world you know, that ties this operation to its mechanism.
 */
public final class C06Top3VehiclesByRevenue {

    private C06Top3VehiclesByRevenue() {
    }

    public static List<String> top3PlatesByRevenue(List<Rental> rentals) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
