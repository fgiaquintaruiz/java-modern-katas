package com.fgiaquinta.katas.kt.language

data class Results(
    val valueOf127Identical: Boolean,
    val valueOfMinus128Identical: Boolean,
    val valueOf128Identical: Boolean,
    val valueOfMinus129Identical: Boolean,
    val valueOf128EqualsOperator: Boolean,
    val literalsIdentical: Boolean,
    val newStringIdenticalToLiteral: Boolean,
    val newStringEqualsOperatorToLiteral: Boolean,
    val internedIdenticalToLiteral: Boolean,
)

/**
 * Exercise E08 (Kotlin twin) — Identity versus equality.
 *
 * Statement: Fill a [Results] by really comparing boxed Integers (declare them as `Int?` so they are
 * boxed) and Strings. Use `===` for identity and `==` for equality, as the names say.
 *
 * Concepts: in Kotlin `==` calls equals and `===` compares references, the opposite meaning of `==` in
 * Java; the same Integer cache (-128 to 127) and string pool sit behind them; `String(...)` constructor
 * calls, or `java.lang.String("abc")`, create a new instance outside the pool.
 *
 * Internals: TODO: fill after solving.
 *
 * Anchor: TODO: your own image, written by you.
 */
fun compare(): Results = TODO("Not solved yet")
