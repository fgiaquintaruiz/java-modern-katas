package com.fgiaquinta.katas.functional.methodref;

import java.util.List;
import java.util.Set;

/**
 * Exercise M05 — Known plates.
 *
 * <h2>Statement</h2>
 * Keep only the plates present in the given set, in input order. A bound instance method reference
 * on the {@code knownPlates} argument is REQUIRED as the predicate; a lambda is not accepted.
 *
 * <h2>Concepts</h2>
 * bound instance method reference ({@code knownPlates::contains}) as a {@code Predicate<String>}.
 *
 * <h2>Internals</h2>
 * TODO after solving: explain what happens inside the pipeline, not what the code does.
 * <h2>Anchor</h2>
 * TODO after solving: one sentence, your own image from a world you know, that ties this operation to its mechanism.
 */
public final class M05KnownPlates {

    private M05KnownPlates() {
    }

    public static List<String> knownPlates(List<String> plates, Set<String> knownPlates) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
