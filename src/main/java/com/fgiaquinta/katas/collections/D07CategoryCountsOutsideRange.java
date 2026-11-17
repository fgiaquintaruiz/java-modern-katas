package com.fgiaquinta.katas.collections;

import com.fgiaquinta.katas.domain.Aircraft;
import com.fgiaquinta.katas.domain.AircraftCategory;

import java.util.EnumMap;
import java.util.List;

/**
 * Exercise D07 — Counts for the categories outside a range.
 *
 * <h2>Statement</h2>
 * Given a list of {@link Aircraft} and a range of categories {@code from} to {@code to} (inclusive,
 * in declaration order), return the aircraft count for every category outside that range. Every such
 * category is a key, in enum order, including those with zero aircraft.
 *
 * <h2>Concepts</h2>
 * {@code EnumSet.range} and {@code EnumSet.complementOf}; {@code EnumMap} keeps keys in ordinal order and is backed by an array indexed by ordinal; why {@code groupingBy} alone cannot produce the zero entries.
 *
 * <h2>Internals</h2>
 * TODO: fill after solving.
 * <h2>Anchor</h2>
 * TODO: your own image, written by you.
 */
public final class D07CategoryCountsOutsideRange {

    private D07CategoryCountsOutsideRange() {
    }

    public static EnumMap<AircraftCategory, Long> countOutsideRange(List<Aircraft> fleet,
                                                                    AircraftCategory from,
                                                                    AircraftCategory to) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
