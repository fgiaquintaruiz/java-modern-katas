package com.fgiaquinta.katas.streams.grouping;

import com.fgiaquinta.katas.domain.Vehicle;
import com.fgiaquinta.katas.domain.VehicleCategory;

import java.util.List;
import java.util.Map;

/**
 * Exercise B01 — Count vehicles by category.
 *
 * <h2>Statement</h2>
 * Given a list of {@link Vehicle}, return a {@link Map} from each {@link VehicleCategory} present
 * in the list to the count of vehicles in that category.
 *
 * <h2>Concepts</h2>
 * {@code Collectors.groupingBy} with {@code counting()} as the downstream; the default map is a {@code HashMap}; the downstream runs independently per key; the count is typed as {@code Long}, not {@code Integer}.
 *
 * <h2>Internals</h2>
 * TODO after solving: explain what happens inside the pipeline, not what the code does.
 * <h2>Anchor</h2>
 * TODO after solving: one sentence, your own image from a world you know, that ties this operation to its mechanism.
 */
public final class B01CountByCategory {

    private B01CountByCategory() {
    }

    public static Map<VehicleCategory, Long> countByCategory(List<Vehicle> vehicles) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
