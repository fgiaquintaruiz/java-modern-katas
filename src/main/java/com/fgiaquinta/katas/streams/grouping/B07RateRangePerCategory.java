package com.fgiaquinta.katas.streams.grouping;

import com.fgiaquinta.katas.domain.Vehicle;
import com.fgiaquinta.katas.domain.VehicleCategory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Exercise B07 — Minimum and maximum daily rate per category, in one pass.
 *
 * <h2>Statement</h2>
 * Given a list of {@link Vehicle}, return a {@link Map} from each {@link VehicleCategory} present
 * in the list to a {@link RateRange} holding the minimum and maximum {@code dailyRate} in that
 * category. The downstream collector for each category MUST be built with {@code Collectors.teeing},
 * computing both bounds by visiting each vehicle exactly once.
 *
 * <h2>Concepts</h2>
 * {@code Collectors.teeing} (Java 12) feeds each element to two collectors, merging results with a {@code BiFunction} instead of running two streams; which {@code Collector.Characteristics} are lost when composing.
 *
 * <h2>Internals</h2>
 * TODO after solving: explain what happens inside the pipeline, not what the code does.
 * <h2>Anchor</h2>
 * TODO after solving: one sentence, your own image from a world you know, that ties this operation to its mechanism.
 */
public final class B07RateRangePerCategory {

    private B07RateRangePerCategory() {
    }

    public static Map<VehicleCategory, RateRange> rateRangePerCategory(List<Vehicle> vehicles) {
        throw new UnsupportedOperationException("Not solved yet");
    }

    /**
     * The minimum and maximum daily rate observed within a category.
     *
     * @param min lowest {@code dailyRate} in the group
     * @param max highest {@code dailyRate} in the group
     */
    public record RateRange(BigDecimal min, BigDecimal max) {
    }
}
