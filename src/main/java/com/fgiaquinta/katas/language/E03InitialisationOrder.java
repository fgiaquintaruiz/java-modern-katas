package com.fgiaquinta.katas.language;

import java.util.List;

/**
 * Exercise E03 — Initialisation order.
 *
 * <h2>Statement</h2>
 * Write a small class inside this one with a static field initialiser, a static block, an instance
 * initialiser block and a constructor, each recording its name in a shared log. Create two instances and
 * return the log. The test fixes the names and the order. Call it once per JVM: the class initialises once.
 *
 * <h2>Concepts</h2>
 * Class initialisation runs once, on first use, in textual order of static initialisers; instance initialiser blocks run on every {@code new}, after the superclass constructor and before the constructor body.
 *
 * <h2>Internals</h2>
 * TODO: fill after solving.
 * <h2>Anchor</h2>
 * TODO: your own image, written by you.
 */
public final class E03InitialisationOrder {

    private E03InitialisationOrder() {
    }

    public static List<String> initialisationOrder() {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
