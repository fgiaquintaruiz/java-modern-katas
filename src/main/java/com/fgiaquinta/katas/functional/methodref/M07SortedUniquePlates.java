package com.fgiaquinta.katas.functional.methodref;

import java.util.List;
import java.util.TreeSet;

/**
 * Exercise M07 — Sorted unique plates.
 *
 * <h2>Statement</h2>
 * Return the distinct plates in natural order as a {@code TreeSet}. A constructor reference is
 * REQUIRED for the collection supplier; a lambda is not accepted.
 *
 * <h2>Concepts</h2>
 * constructor reference ({@code TreeSet::new}) as a {@code Supplier}; {@code Collectors.toCollection}.
 *
 * <h2>Internals</h2>
 * TODO after solving: explain what happens inside the pipeline, not what the code does.
 * <h2>Anchor</h2>
 * TODO after solving: one sentence, your own image from a world you know, that ties this operation to its mechanism.
 */
public final class M07SortedUniquePlates {

    private M07SortedUniquePlates() {
    }

    public static TreeSet<String> sortedUniquePlates(List<String> plates) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
