package com.fgiaquinta.katas.collections;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;

/**
 * Exercise D01 — Boarding queue and last boarded.
 *
 * <h2>Statement</h2>
 * Passengers board a charter flight in the order of the given list, first in, first out. Return the
 * last {@code n} passengers who boarded, most recent first. The input list must not be modified.
 *
 * <h2>Concepts</h2>
 * {@code ArrayDeque} used as a queue ({@code offer}/{@code poll}) and as a stack ({@code push}/{@code pop}); the same class, different end of the deque.
 *
 * <h2>Internals</h2>
 * 1. We skip {@code size - n} passengers so we focus on the last passengers of the boarding list.
 * 2. Each push updates the head index, which means one more slot in the ring is occupied.
 * 3. The stream walks the ring from head to tail: if head is index 13 it starts there and continues with 14, 15, 0, 1, 2 and 3 when tail is index 3.
 * <h2>Anchor</h2>
 * This behaves like a toy car with a spring in the rear wheels: when we add a passenger we move the car one click back and charge the spring, and reading the stream is the same as releasing the car.
 *
 * <h2>Log</h2>
 * {@code docs/log/collections-d01-boarding-queue.md}
 *
 * Alternative
 * int size = boardingOrder.size();
 * return  boardingOrder.subList(size - n, size).reversed();
 */
public final class D01BoardingQueue {

    private D01BoardingQueue() {
    }

    public static List<String> lastBoardedFirst(List<String> boardingOrder, int n) {
        if (n == 0 || boardingOrder.isEmpty()) {
            return  Collections.emptyList();
        }
        ArrayDeque<String> queue = new ArrayDeque<>();

        int size = boardingOrder.size();
        boardingOrder.stream()
                .skip(Math.max(0, size - n))
                .forEach(queue::push);
        return queue.stream().toList();
    }
}
