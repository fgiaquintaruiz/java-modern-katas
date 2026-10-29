package com.fgiaquinta.katas.functional.methodref;

import java.util.List;
import java.util.Set;

/**
 * Exercise M05 — Known registrations.
 *
 * <h2>Statement</h2>
 * Keep only the registrations present in the given set, in input order. A bound instance method reference
 * on the {@code knownRegistrations} argument is REQUIRED as the predicate; a lambda is not accepted.
 *
 * <h2>Concepts</h2>
 * bound instance method reference ({@code knownRegistrations::contains}) as a {@code Predicate<String>}.
 *
 * <h2>Internals</h2>
 * TODO after solving: explain what happens inside the pipeline, not what the code does.
 * <h2>Anchor</h2>
 * TODO after solving: one sentence, your own image from a world you know, that ties this operation to its mechanism.
 */
public final class M05KnownRegistrations {

    private M05KnownRegistrations() {
    }

    public static List<String> knownRegistrations(List<String> registrations, Set<String> knownRegistrations) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
