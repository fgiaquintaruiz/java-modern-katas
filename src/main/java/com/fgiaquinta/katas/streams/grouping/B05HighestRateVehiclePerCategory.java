package com.fgiaquinta.katas.streams.grouping;

import com.fgiaquinta.katas.domain.Vehicle;
import com.fgiaquinta.katas.domain.VehicleCategory;

import java.util.List;
import java.util.Map;

/**
 * Exercise B05 — Highest daily rate vehicle per category.
 *
 * <h2>Statement</h2>
 * Given a list of {@link Vehicle}, return a {@link Map} from each {@link VehicleCategory} present
 * in the list to the vehicle with the highest {@code dailyRate} within that category.
 *
 * <h2>Concepts</h2>
 * {@code maxBy} returns an {@code Optional} since a group could in theory be empty; {@code collectingAndThen(maxBy(...), Optional::get)} is safe because {@code groupingBy} never creates empty groups; ties keep the first operand.
 *
 * <h2>Internals</h2>
 * TODO after solving: explain what happens inside the pipeline, not what the code does.
 * <h2>Anchor</h2>
 * TODO after solving: one sentence, your own image from a world you know, that ties this operation to its mechanism.
 */
public final class B05HighestRateVehiclePerCategory {

    private B05HighestRateVehiclePerCategory() {
    }

    public static Map<VehicleCategory, Vehicle> highestRatePerCategory(List<Vehicle> vehicles) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
