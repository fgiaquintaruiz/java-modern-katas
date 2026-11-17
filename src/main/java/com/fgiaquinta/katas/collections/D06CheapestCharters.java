package com.fgiaquinta.katas.collections;

import com.fgiaquinta.katas.domain.Charter;

import java.util.List;
import java.util.PriorityQueue;

/**
 * Exercise D06 — The k cheapest charters.
 *
 * <h2>Statement</h2>
 * Return a queue holding the {@code k} charters with the lowest total price, so that polling it yields
 * them from cheapest to most expensive. If there are fewer than {@code k} charters, the queue holds all
 * of them.
 *
 * <h2>Concepts</h2>
 * {@code PriorityQueue} is a binary heap: {@code poll} is ordered, iteration and {@code toString} are not; {@code Comparator.comparing(Charter::totalPrice)}; {@code offer}/{@code poll} cost O(log n), {@code peek} O(1).
 *
 * <h2>Internals</h2>
 * TODO: fill after solving.
 * <h2>Anchor</h2>
 * TODO: your own image, written by you.
 */
public final class D06CheapestCharters {

    private D06CheapestCharters() {
    }

    public static PriorityQueue<Charter> cheapest(List<Charter> charters, int k) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
