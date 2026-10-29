package com.fgiaquinta.katas.streams.flatmap;

import com.fgiaquinta.katas.domain.Charter;

import java.util.List;

/**
 * Exercise A05 — Charter extra lines.
 *
 * <h2>Statement</h2>
 * Given a list of {@link Charter}, produce one formatted line per extra attached to each charter,
 * using the format {@code "<charterId>:<extraName>"}. A charter with no extras contributes no lines
 * at all.
 *
 * <h2>Concepts</h2>
 * {@code flatMap} yielding variable-size inner streams, including {@code Stream.empty()}; one inner {@code Stream} allocated per outer element; why {@code map} plus {@code filter} cannot express a one-to-many expansion.
 *
 * <h2>Internals</h2>
 * TODO after solving: explain what happens inside the pipeline, not what the code does.
 * <h2>Anchor</h2>
 * TODO after solving: one sentence, your own image from a world you know, that ties this operation to its mechanism.
 */
public final class A05CharterExtraLines {

    private A05CharterExtraLines() {
    }

    public static List<String> extraLines(List<Charter> charters) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
