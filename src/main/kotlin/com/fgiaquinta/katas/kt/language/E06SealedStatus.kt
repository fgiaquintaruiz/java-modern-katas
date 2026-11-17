package com.fgiaquinta.katas.kt.language

import com.fgiaquinta.katas.domain.Charter

sealed interface CharterStatus {
    data class Scheduled(val charter: Charter) : CharterStatus
    data class Delayed(val minutes: Int) : CharterStatus
    data class Cancelled(val reason: String) : CharterStatus
    data object Boarding : CharterStatus
}

/**
 * Exercise E06 (Kotlin twin) — Sealed hierarchy and `when`.
 *
 * Statement: Describe a [CharterStatus] with a `when` expression and no `else` branch. Scheduled gives
 * "Scheduled R1" (the charter id), Delayed gives "Delayed 15 min", or "Heavily delayed 90 min" from 60
 * minutes up, Cancelled gives "Cancelled: weather", and the Boarding object gives "Boarding".
 *
 * Concepts: `sealed interface` with data class and `data object` subtypes; `when` used as an expression
 * must be exhaustive, so the compiler rejects a missing subtype; `is` checks smart-cast the subject, so
 * no explicit cast or deconstruction is needed; an object subtype is matched by equality, not by `is`.
 *
 * Internals: TODO: fill after solving.
 *
 * Anchor: TODO: your own image, written by you.
 */
fun describe(status: CharterStatus): String = TODO("Not solved yet")
