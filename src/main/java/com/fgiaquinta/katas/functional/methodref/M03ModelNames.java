package com.fgiaquinta.katas.functional.methodref;

import com.fgiaquinta.katas.domain.Aircraft;

import java.util.List;

/**
 * Exercise M03 — Model names.
 *
 * <h2>Statement</h2>
 * Return the model of each aircraft, in input order. An unbound instance method reference (the first
 * argument becomes the receiver) is REQUIRED; a lambda is not accepted.
 *
 * <h2>Concepts</h2>
 * unbound instance method reference ({@code Aircraft::model}) as a {@code Function<Aircraft, String>}.
 *
 * <h2>Internals</h2>
 * TODO after solving: explain what happens inside the pipeline, not what the code does.
 * <h2>Anchor</h2>
 * TODO after solving: one sentence, your own image from a world you know, that ties this operation to its mechanism.
 */
public final class M03ModelNames {

    private M03ModelNames() {
    }

    public static List<String> modelNames(List<Aircraft> aircraft) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
