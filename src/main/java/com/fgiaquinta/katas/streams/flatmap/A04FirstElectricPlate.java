package com.fgiaquinta.katas.streams.flatmap;

import com.fgiaquinta.katas.domain.Branch;

import java.util.List;
import java.util.Optional;

/**
 * Exercise A04 — First electric plate.
 *
 * <h2>Statement</h2>
 * Given a list of {@link Branch}, scan branches and their fleets in order for the first vehicle
 * whose fuel type is {@code ELECTRIC}, and return its plate wrapped in an {@code Optional<String>}.
 * The scan must stop logically at the first match; nothing after it needs to be inspected for the
 * result to be correct.
 *
 * <h2>Concepts</h2>
 * {@code findFirst} as a short-circuiting terminal; {@code flatMap} laziness across the inner stream (eager pre-JDK 10, lazy from JDK 10 on); encounter order and {@code findFirst} versus {@code findAny}.
 *
 * <h2>Internals</h2>
 * TODO after solving: explain what happens inside the pipeline, not what the code does.
 * <h2>Anchor</h2>
 * TODO after solving: one sentence, your own image from a world you know, that ties this operation to its mechanism.
 */
public final class A04FirstElectricPlate {

    private A04FirstElectricPlate() {
    }

    public static Optional<String> firstElectricPlate(List<Branch> branches) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
