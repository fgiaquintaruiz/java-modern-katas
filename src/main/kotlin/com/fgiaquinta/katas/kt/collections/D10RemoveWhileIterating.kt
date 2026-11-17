package com.fgiaquinta.katas.kt.collections

import com.fgiaquinta.katas.domain.Charter

/**
 * Exercise D10 (Kotlin twin) — Remove while iterating.
 *
 * Statement: Remove from the given mutable list every charter lasting fewer than [minDays] days, in
 * place, and return the same list instance. Charters lasting exactly [minDays] stay, and the order of
 * the survivors does not change.
 *
 * Concepts: removeIf, MutableList.removeAll { } and MutableIterator.remove mutate in place; filter and
 * filterNot return a new list, so they fail the same-instance requirement; a for-each over the list
 * with remove inside throws ConcurrentModificationException.
 *
 * Internals: TODO: fill after solving.
 *
 * Anchor: TODO: your own image, written by you.
 */
fun removeShorterThan(charters: MutableList<Charter>, minDays: Long): MutableList<Charter> =
    TODO("Not solved yet")
