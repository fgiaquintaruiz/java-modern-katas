package com.fgiaquinta.katas.kt.collections

import com.fgiaquinta.katas.domain.Charter
import java.time.LocalTime

/**
 * Exercise D04 (Kotlin twin) — Departure neighbours.
 *
 * Statement: Given a departure schedule (departure time to charter, in no particular order) and a time,
 * return a pair (previous, next): the last charter departing strictly before that time and the first
 * charter departing at or after it. Either side is null when there is none.
 *
 * Concepts: toSortedMap() and sortedMapOf() build a java.util.TreeMap but are typed as SortedMap, which
 * has no ceilingEntry/floorEntry, so the variable needs the java.util.TreeMap or java.util.NavigableMap
 * type; ceilingEntry/lowerEntry return a nullable entry, so a Kotlin nullable pair replaces Optional.
 *
 * Internals: TODO: fill after solving.
 *
 * Anchor: TODO: your own image, written by you.
 */
fun neighbours(schedule: Map<LocalTime, Charter>, at: LocalTime): Pair<Charter?, Charter?> =
    TODO("Not solved yet")
