package com.fgiaquinta.katas.language;

import com.fgiaquinta.katas.domain.Charter;

/**
 * Exercise E06 — Sealed hierarchy and pattern-matching switch.
 *
 * <h2>Statement</h2>
 * Describe a {@link Status} with a switch expression over record patterns and no {@code default} branch.
 * Scheduled gives {@code "Scheduled R1"} (the charter id), Delayed gives {@code "Delayed 15 min"}, or
 * {@code "Heavily delayed 90 min"} from 60 minutes up, and Cancelled gives {@code "Cancelled: weather"}.
 *
 * <h2>Concepts</h2>
 * {@code sealed interface} with {@code permits}; record deconstruction patterns; a {@code when} guard; exhaustiveness checked by the compiler, so adding a permitted type breaks every switch without a default.
 *
 * <h2>Internals</h2>
 * TODO: fill after solving.
 * <h2>Anchor</h2>
 * TODO: your own image, written by you.
 */
public final class E06SealedStatus {

    private E06SealedStatus() {
    }

    public sealed interface Status permits Scheduled, Delayed, Cancelled {
    }

    public record Scheduled(Charter charter) implements Status {
    }

    public record Delayed(int minutes) implements Status {
    }

    public record Cancelled(String reason) implements Status {
    }

    public static String describe(Status status) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
