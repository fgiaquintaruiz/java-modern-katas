package com.fgiaquinta.katas.functional.methodref;

import com.fgiaquinta.katas.domain.Extra;

import java.math.BigDecimal;
import java.util.List;

/**
 * Exercise M08 — Flat priced extras.
 *
 * <h2>Statement</h2>
 * Build one {@code Extra} per name, all with the given price, and return them as an array in input
 * order. A constructor reference held as a {@code BiFunction<String, BigDecimal, Extra>} and an array
 * constructor reference are REQUIRED; lambdas are not accepted.
 *
 * <h2>Concepts</h2>
 * two-argument constructor reference ({@code Extra::new}); array constructor reference ({@code Extra[]::new}).
 *
 * <h2>Internals</h2>
 * TODO after solving: explain what happens inside the pipeline, not what the code does.
 * <h2>Anchor</h2>
 * TODO after solving: one sentence, your own image from a world you know, that ties this operation to its mechanism.
 */
public final class M08FlatPricedExtras {

    private M08FlatPricedExtras() {
    }

    public static Extra[] flatPricedExtras(List<String> names, BigDecimal price) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
