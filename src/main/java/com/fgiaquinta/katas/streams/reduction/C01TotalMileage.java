package com.fgiaquinta.katas.streams.reduction;

import com.fgiaquinta.katas.domain.Vehicle;

import java.util.List;

/**
 * Exercise C01 — Total Mileage.
 *
 * <h2>Statement</h2>
 * Given a list of {@link Vehicle}, return the sum of their {@code mileageKm} values as a single
 * {@code long}.
 *
 * <h2>Concepts</h2>
 * {@code mapToLong} turning a {@code Stream<Vehicle>} into a primitive {@code LongStream}, avoiding boxing; {@code LongStream.sum()} as a specialised {@code reduce} over primitives; primitive streams versus {@code Stream<Long>} and the boxing cost.
 *
 * <h2>Internals</h2>
 * TODO after solving: explain what happens inside the pipeline, not what the code does.
 * <h2>Anchor</h2>
 * TODO after solving: one sentence, your own image from a world you know, that ties this operation to its mechanism.
 */
public final class C01TotalMileage {

    private C01TotalMileage() {
    }

    public static long totalMileage(List<Vehicle> vehicles) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
