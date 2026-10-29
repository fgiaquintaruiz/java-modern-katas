package com.fgiaquinta.katas.kt.flatmap

import com.fgiaquinta.katas.domain.Airport

/**
 * Exercise A01 (Kotlin twin) — All registrations.
 *
 * Statement: Given a list of [Airport], flatten the fleets of all airports into a single list of registrations.
 * This is the canonical one-to-many flattening exercise: airports map to fleets, and fleets map to registrations.
 *
 * Concepts: flatMap on a List is eager: every call allocates a full new list; compare with asSequence().
 *
 * Internals: In the first sequence version the fleet receives the inner map call; it is an eager map that allocates a list per airport, three lists, plus the one from toList.
 * In the kept version the lambda returns a list of aircraft and flatMap returns a sequence; one aircraft at a time arrives at map, and the list from toList is the only one allocated.
 *
 * Anchor: It is like looking to the left before crossing the street, to check if it is lazy or not instead of looking if a car comes.
 */
fun allRegistrations(airports: List<Airport>): List<String> = airports
    .asSequence().flatMap { airport -> airport.fleet }
    .map { aircraft -> aircraft.registration }
    .toList()