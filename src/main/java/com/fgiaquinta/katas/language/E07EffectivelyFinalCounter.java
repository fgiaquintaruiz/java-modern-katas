package com.fgiaquinta.katas.language;

import com.fgiaquinta.katas.domain.Charter;

import java.util.List;

/**
 * Exercise E07 — Effectively final and lambda capture.
 *
 * <h2>Statement</h2>
 * Count the charters lasting at least {@code minDays} days. Do it in one pass with {@code forEach}, not
 * with {@code count()}: a lambda may only capture locals that are final or effectively final, so
 * {@code int counter = 0; ... counter++} inside it does not compile.
 *
 * <h2>Concepts</h2>
 * Effectively final locals; the lambda captures a copy of the value, so a mutable counter has to live in an object ({@code AtomicInteger}, a one-element array); why the language forbids the shortcut.
 *
 * <h2>Internals</h2>
 * TODO: fill after solving.
 * <h2>Anchor</h2>
 * TODO: your own image, written by you.
 */
public final class E07EffectivelyFinalCounter {

    private E07EffectivelyFinalCounter() {
    }

    public static int countLongCharters(List<Charter> charters, long minDays) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
