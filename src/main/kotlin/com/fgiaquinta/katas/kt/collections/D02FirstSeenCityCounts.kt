package com.fgiaquinta.katas.kt.collections

import com.fgiaquinta.katas.domain.Airport

/**
 * Exercise D02 (Kotlin twin) — Aircraft per city, first-seen order.
 *
 * Statement: Given a list of [Airport], return a map from city to the total number of aircraft in the
 * fleets of the airports in that city. Cities must iterate in the order they first appear in the list.
 *
 * Concepts: mutableMapOf() and the default mapOf/associate/groupBy results are already a
 * java.util.LinkedHashMap, so first-seen order comes for free and no explicit LinkedHashMap::new is
 * needed (unlike Collectors.toMap in Java); getOrPut / merge / groupBy then sumOf; re-putting an
 * existing key keeps its position.
 *
 * Internals: TODO: fill after solving.
 *
 * Anchor: TODO: your own image, written by you.
 */
fun aircraftCountByCity(airports: List<Airport>): Map<String, Int> =
    TODO("Not solved yet")
