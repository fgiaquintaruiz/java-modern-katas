package com.fgiaquinta.katas.kt.flatmap

import com.fgiaquinta.katas.domain.Airport

/**
 * Exercise A02 (Kotlin twin) — Distinct features sorted.
 *
 * Statement: Given a list of [Airport], collect the distinct feature tags of every aircraft across all
 * airports, sorted alphabetically. Two levels of nesting must be flattened: airports into aircraft,
 * and aircraft into feature tags.
 *
 * Concepts: distinct() and sorted() on a List vs on a Sequence; which one still needs a barrier.
 *
 * Internals: A sequence is opened on airports, so one airport is passed down. The first flatMap
 * flattens its fleet and one aircraft is passed down to the second flatMap, which flattens the
 * features on it. Then one feature is passed to distinct, which creates a HashSet and tries to add
 * it: if it can, the feature passes to sorted, otherwise it is dropped. sorted creates a mutable
 * list and adds each feature as distinct hands it down; when sorted has all the elements it
 * actually sorts them, once, and passes down an iterator to toList, which returns the final list
 * of features, deduplicated and sorted in natural order.
 *
 * Anchor: A production line of birthday candles numbered 1 to 10, with duplicates, that arrive
 * inside a box, and that box inside another box; the line takes them out of the boxes, removes the
 * duplicates, puts them in a new box ordered from 1 to 10, and then the box is closed and
 * delivered.
 */
fun distinctFeaturesSorted(airports: List<Airport>): List<String> =
    airports.asSequence()
    .flatMap { airport -> airport.fleet }
    .flatMap { aircraft -> aircraft.features }
    .distinct().sorted().toList()
