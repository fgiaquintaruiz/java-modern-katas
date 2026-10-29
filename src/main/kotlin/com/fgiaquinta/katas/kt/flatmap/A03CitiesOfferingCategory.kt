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
 * Internals: TODO after solving: explain what happens inside the pipeline, not what the code does.
 *
 * Anchor: TODO after solving: one sentence, your own image from a world you know, that ties this operation to its mechanism.
 */
fun citiesOffering(airports: List<Airport>, category: AircraftCategory): Set<String> = TODO("Not solved yet")
