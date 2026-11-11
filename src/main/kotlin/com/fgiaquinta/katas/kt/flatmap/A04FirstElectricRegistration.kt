package com.fgiaquinta.katas.kt.flatmap

import com.fgiaquinta.katas.domain.Airport
import com.fgiaquinta.katas.domain.EngineType

/**
 * Exercise A04 (Kotlin twin) — First electric registration.
 *
 * Statement: Given a list of [Airport], scan airports and their fleets in order for the first aircraft
 * whose engine type is `ELECTRIC`, and return its registration, or null when there is none. The scan
 * must stop at the first match; nothing after it needs to be inspected for the result to be correct.
 *
 * Concepts: Sequence vs eager List operations; firstOrNull as a short-circuiting terminal; laziness
 * across the inner collection; nullable return instead of Optional.
 *
 * Internals: the sequence pulls an airport first, then flatMap pulls its aircraft one at a time, and
 * firstOrNull pulls until the first aircraft whose engine type is ELECTRIC. It goes through the
 * aircraft one at a time and stops as soon as it finds the electric one; if that is in airport two,
 * airport three is never opened.
 *
 * Anchor: while shopping for a red ball I go through the balls on the shelf, pick the first red one
 * I find and check out.
 */
fun firstElectricRegistration(airports: List<Airport>): String? = airports
    .asSequence()
    .flatMap { airport -> airport.fleet() }
    .firstOrNull{ it.engine == EngineType.ELECTRIC }
    ?.registration

