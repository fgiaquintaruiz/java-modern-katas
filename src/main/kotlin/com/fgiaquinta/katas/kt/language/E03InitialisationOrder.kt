package com.fgiaquinta.katas.kt.language

/**
 * Exercise E03 (Kotlin twin) — Initialisation order.
 *
 * Statement: Write a small class with a companion object `init` block, a property initialiser, an `init`
 * block in the class and a secondary constructor body, each recording its name in a shared log. Log
 * "before" first, then create two instances and return the log. The test fixes the names and the order.
 * Call it once per JVM.
 *
 * Concepts: the companion object initialises lazily, on the first use of the class, not at program start;
 * property initialisers and `init` blocks run in textual order as part of the primary constructor; a
 * secondary constructor body runs after them.
 *
 * Internals: TODO: fill after solving.
 *
 * Anchor: TODO: your own image, written by you.
 */
fun initialisationOrder(): List<String> = TODO("Not solved yet")
