package com.fgiaquinta.katas.kt.language

import java.math.BigDecimal

/** Stub: equality and toString come from the primary constructor only; the body property is excluded. */
data class Quote(val route: String, val price: BigDecimal) {
    var note: String = ""
}

/**
 * Exercise E05 (Kotlin twin) — Data class internals.
 *
 * Statement: Complete [Quote] with validation and implement [quote]: a negative price is rejected with
 * IllegalArgumentException, a zero price is accepted, the route is stored without surrounding whitespace,
 * and the given note ends up in the `note` property.
 *
 * Concepts: `data class` generates equals, hashCode, toString, copy and componentN from the primary
 * constructor properties only, so `note`, declared in the body, is left out; `init { require(...) }`
 * is the counterpart of a compact constructor; a data class is final unless you open it.
 *
 * Internals: TODO: fill after solving.
 *
 * Anchor: TODO: your own image, written by you.
 */
fun quote(route: String, price: BigDecimal, note: String = ""): Quote = TODO("Not solved yet")
