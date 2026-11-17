package com.fgiaquinta.katas.kt.collections

class Views(
    val readOnlyView: List<String>,
    val copy: List<String>,
    val javaImmutable: List<String>,
    val firstTwoView: MutableList<String>,
    val asListOfArray: MutableList<String>,
    val backingArray: Array<String>,
)

/**
 * Exercise D09 (Kotlin twin) — Views versus copies.
 *
 * Statement: Given a mutable list of registrations (at least two elements), build six collections from
 * it and return them in a [Views]: a read-only List typed view of the same list, an independent copy,
 * a java.util.List.of copy, the view of its first two elements, a list over a new array holding the
 * same elements, and that array. Which ones follow later changes of the source is what the tests check.
 *
 * Concepts: listOf()/List<T> is read-only, not immutable (a cast or a Java caller can still mutate the
 * source behind it, and the view follows it); toList() is a copy; java.util.List.copyOf throws on
 * writes; subList is a view and breaks after a structural change of the source; array.asList() writes
 * through to the array.
 *
 * Internals: TODO: fill after solving.
 *
 * Anchor: TODO: your own image, written by you.
 */
fun build(source: MutableList<String>): Views =
    TODO("Not solved yet")
