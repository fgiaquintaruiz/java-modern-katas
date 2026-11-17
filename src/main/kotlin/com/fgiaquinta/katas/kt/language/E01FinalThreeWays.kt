package com.fgiaquinta.katas.kt.language

/** Stub: the shape (properties, companion constant, method) is yours to design. */
class Holder

/**
 * Exercise E01 (Kotlin twin) — final three ways.
 *
 * Statement: Design [Holder] and implement [hold]: the holder keeps the given list, as it is, in a
 * property named `registrations`, declares a method `describe(): String`, and exposes a compile-time
 * constant `CAPACITY` in its companion object.
 *
 * Concepts: classes and members are final by default and `open` opts in (the opposite of Java); `val`
 * freezes the reference, not the contents; `const val` becomes a public static final field, a plain
 * `val` in a companion becomes a private static field behind a getter.
 *
 * Internals: TODO: fill after solving.
 *
 * Anchor: TODO: your own image, written by you.
 */
fun hold(registrations: MutableList<String>): Holder = TODO("Not solved yet")
