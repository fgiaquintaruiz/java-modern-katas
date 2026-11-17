package com.fgiaquinta.katas.kt.collections

/**
 * Mutable on purpose: a data class derives equals and hashCode from every primary-constructor property,
 * and this one has a `var`.
 */
data class MutableRoute(var code: String)

/**
 * Exercise D08 (Kotlin twin) — Lookup after a key mutates.
 *
 * Statement: Register a [MutableRoute] in a hash-based set, then rename the route to [newCode]. Return
 * whether the registry still finds the route after the rename. The route must end up renamed, and the
 * answer must be true.
 *
 * Concepts: a data class generates equals/hashCode from the primary constructor, so a `var` property in
 * it is the trap; hashSetOf/HashMap place the entry by the hash at insertion time; copy() gives an
 * independent key, and a class with only `val` properties is safe as a key.
 *
 * Internals: TODO: fill after solving.
 *
 * Anchor: TODO: your own image, written by you.
 */
fun containsAfterRename(route: MutableRoute, newCode: String): Boolean =
    TODO("Not solved yet")
