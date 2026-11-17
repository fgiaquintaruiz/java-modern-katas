package com.fgiaquinta.katas.kt.flatmap

import com.fgiaquinta.katas.domain.Airport
import com.fgiaquinta.katas.domain.AircraftCategory

/**
 * Exercise A03 (Kotlin twin) — Cities offering a category.
 *
 * Statement: Given a list of [Airport] and a [AircraftCategory], return the set of city names of
 * airports that have at least one aircraft of that category in their fleet. Two airports sharing the
 * same city collapse into a single entry, since city identity is what matters here, not airport identity.
 *
 * Concepts: any {} on the inner list as short-circuit; toSet() vs toHashSet().
 *
 * Internals:
 * ToSet asks for the first airport, then it is filtered by a contained aircraft category matching, then the map gets the city
 * of that airport and the city name is added to the set
 * Any looks at 2 aircraft of 5 if the second one matches and then the pipeline goes back to the filter for the next airport.
 * When having 2 airports in the same city with matching category, they meet when the set does the .add and survives the one
 * added first, because the set does not allow duplicates
 *
 * Anchor:
 * When browsing in a store shelf for a red basket ball in boxes that contain more boxes inside, I put the red ball in my
 * shopping cart and opening another big boxes I find another red basket ball but then I look at the cart I already have
 * one so I put it away
 *
 * Log: docs/log/kt-flatmap-a03-cities-offering-category.md
 */
fun citiesOffering(airports: List<Airport>, category: AircraftCategory): Set<String> = airports.asSequence()
    .filter { airport -> airport.fleet.any { category == it.category} }
    .map { it.city }
    .toSet()
