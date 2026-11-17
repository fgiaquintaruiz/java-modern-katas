package com.fgiaquinta.katas.kt.collections

import com.fgiaquinta.katas.domain.Charter
import java.util.PriorityQueue

/**
 * Exercise D06 (Kotlin twin) — The k cheapest charters.
 *
 * Statement: Return a queue holding the [k] charters with the lowest total price, so that polling it
 * yields them from cheapest to most expensive. If there are fewer than [k] charters, the queue holds
 * all of them.
 *
 * Concepts: Kotlin has no PriorityQueue of its own, so java.util.PriorityQueue is imported;
 * compareBy { it.totalPrice() } builds the Comparator (Charter is a Java record, so the accessor is
 * totalPrice() or the synthetic property totalPrice depending on the Kotlin version); poll is ordered,
 * iteration is not.
 *
 * Internals: TODO: fill after solving.
 *
 * Anchor: TODO: your own image, written by you.
 */
fun cheapest(charters: List<Charter>, k: Int): PriorityQueue<Charter> =
    TODO("Not solved yet")
