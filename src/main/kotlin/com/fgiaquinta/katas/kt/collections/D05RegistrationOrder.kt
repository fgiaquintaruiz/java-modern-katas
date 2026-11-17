package com.fgiaquinta.katas.kt.collections

import com.fgiaquinta.katas.domain.Aircraft
import java.util.NavigableSet

/**
 * Exercise D05 (Kotlin twin) — Registrations ordered by length, then alphabetically.
 *
 * Statement: Return the registrations of the given aircraft as a navigable set ordered by length first
 * and then alphabetically ignoring case. Two registrations that differ only in case are the same element.
 *
 * Concepts: sortedSetOf(comparator, ...) and toSortedSet(comparator) return a java.util.TreeSet (typed
 * SortedSet, so declare NavigableSet or TreeSet to reach higher/lower); compareBy<String> { it.length }
 * combined with thenBy(String.CASE_INSENSITIVE_ORDER); the comparator, not equals, decides duplicates.
 *
 * Internals: TODO: fill after solving.
 *
 * Anchor: TODO: your own image, written by you.
 */
fun orderedRegistrations(fleet: List<Aircraft>): NavigableSet<String> =
    TODO("Not solved yet")
