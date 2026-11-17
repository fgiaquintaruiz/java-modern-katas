package com.fgiaquinta.katas.kt.language

import com.fgiaquinta.katas.domain.Charter

/**
 * Exercise E07 (Kotlin twin) — Capturing and mutating a local in a lambda.
 *
 * Statement: Count the charters lasting at least [minDays] days with a local `var` that a `forEach`
 * lambda increments. In Java the same code does not compile; here it does.
 *
 * Concepts: a Kotlin lambda can capture and modify a `var`; the compiler wraps the variable in a
 * kotlin.jvm.internal.Ref.IntRef so the lambda and the function share one box; the captured variable
 * then outlives the stack frame.
 *
 * Internals: TODO: fill after solving.
 *
 * Anchor: TODO: your own image, written by you.
 */
fun countLongCharters(charters: List<Charter>, minDays: Long): Int = TODO("Not solved yet")
