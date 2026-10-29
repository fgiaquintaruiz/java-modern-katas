package com.fgiaquinta.katas.streams.reduction;

import com.fgiaquinta.katas.domain.Charter;

import java.util.List;
import java.util.Optional;

/**
 * Exercise C04 — Longest Charter.
 *
 * <h2>Statement</h2>
 * Given a list of {@link Charter}, return the one with the greatest {@code days()}, wrapped in an
 * {@link Optional}.
 *
 * <h2>Concepts</h2>
 * {@code max(Comparator)} as {@code reduce(maxBy(comparator))}; {@code Optional} since the stream may be empty; {@code comparingLong} avoids boxing; {@code maxBy} keeps the first operand on ties.
 *
 * <h2>Internals</h2>
 * TODO after solving: explain what happens inside the pipeline, not what the code does.
 * <h2>Anchor</h2>
 * TODO after solving: one sentence, your own image from a world you know, that ties this operation to its mechanism.
 */
public final class C04LongestCharter {

    private C04LongestCharter() {
    }

    public static Optional<Charter> longestCharter(List<Charter> charters) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
