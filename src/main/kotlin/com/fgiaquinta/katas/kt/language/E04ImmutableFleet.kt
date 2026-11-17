package com.fgiaquinta.katas.kt.language

import com.fgiaquinta.katas.domain.Aircraft

/** Stub: `val` alone does not make the list immutable; hardening this class is the exercise. */
data class Fleet(val owner: String, val aircraft: List<Aircraft>)

/**
 * Exercise E04 (Kotlin twin) — Immutable class.
 *
 * Statement: Make [Fleet] immutable and implement [create]. Changing the list passed in, or the list
 * read from `aircraft`, even through a cast to MutableList, must never change the fleet.
 *
 * Concepts: a data class with `val` freezes references only; `List` is read-only, not immutable, so a
 * cast or a Java caller can still mutate it; toList() copies; a constructor parameter that is not a
 * property can be copied into a private property.
 *
 * Internals: TODO: fill after solving.
 *
 * Anchor: TODO: your own image, written by you.
 */
fun create(owner: String, aircraft: List<Aircraft>): Fleet = TODO("Not solved yet")
