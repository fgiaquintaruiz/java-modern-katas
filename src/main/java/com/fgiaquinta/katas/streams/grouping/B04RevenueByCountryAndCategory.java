package com.fgiaquinta.katas.streams.grouping;

import com.fgiaquinta.katas.domain.Rental;
import com.fgiaquinta.katas.domain.VehicleCategory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Exercise B04 — Revenue grouped by country, then by vehicle category.
 *
 * <h2>Statement</h2>
 * Given a list of {@link Rental}, return a {@link Map} from the pickup branch's country to a nested
 * {@link Map} from {@link VehicleCategory} to the total {@code totalPrice} of rentals that picked up
 * a vehicle of that category in that country.
 *
 * <h2>Concepts</h2>
 * nested {@code groupingBy} (country, then category); {@code Collectors.reducing} as the innermost downstream versus {@code summingDouble}; one map allocated per country plus the outer map.
 *
 * <h2>Internals</h2>
 * TODO after solving: explain what happens inside the pipeline, not what the code does.
 * <h2>Anchor</h2>
 * TODO after solving: one sentence, your own image from a world you know, that ties this operation to its mechanism.
 */
public final class B04RevenueByCountryAndCategory {

    private B04RevenueByCountryAndCategory() {
    }

    public static Map<String, Map<VehicleCategory, BigDecimal>> revenueByCountryAndCategory(List<Rental> rentals) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
