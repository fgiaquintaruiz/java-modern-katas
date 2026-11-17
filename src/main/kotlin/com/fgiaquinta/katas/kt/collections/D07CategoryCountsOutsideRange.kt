package com.fgiaquinta.katas.kt.collections

import com.fgiaquinta.katas.domain.Aircraft
import com.fgiaquinta.katas.domain.AircraftCategory
import java.util.EnumMap

/**
 * Exercise D07 (Kotlin twin) — Counts for the categories outside a range.
 *
 * Statement: Given a list of [Aircraft] and a range of categories [from] to [to] (inclusive, in
 * declaration order), return the aircraft count for every category outside that range. Every such
 * category is a key, in enum order, including those with zero aircraft.
 *
 * Concepts: java.util.EnumMap and java.util.EnumSet are imported (Kotlin has no own versions);
 * EnumSet.range and EnumSet.complementOf; enumValues<AircraftCategory>() is the Kotlin way to get the
 * constants, and AircraftCategory.entries is the modern alternative to values().
 *
 * Internals: TODO: fill after solving.
 *
 * Anchor: TODO: your own image, written by you.
 */
fun countOutsideRange(fleet: List<Aircraft>, from: AircraftCategory, to: AircraftCategory): EnumMap<AircraftCategory, Long> =
    TODO("Not solved yet")
