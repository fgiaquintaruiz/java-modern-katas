package com.fgiaquinta.katas.kt.collections

import com.fgiaquinta.katas.domain.Charter
import java.util.LinkedHashMap

/**
 * Exercise D03 (Kotlin twin) — Recently viewed charters.
 *
 * Statement: A screen remembers the last [capacity] distinct charters a user viewed. Given the sequence
 * of views, return a map from charter id to charter whose keys run from least to most recently viewed.
 * Viewing a charter again makes it the most recent; when the capacity is exceeded the least recently
 * viewed one is dropped.
 *
 * Concepts: Kotlin has no access-order map of its own, so it must be java.util.LinkedHashMap(capacity,
 * 0.75f, true) built as an anonymous subclass with `object : LinkedHashMap<String, Charter>(...)`
 * overriding removeEldestEntry; the override takes a MutableMap.MutableEntry and returns Boolean.
 *
 * Internals: TODO: fill after solving.
 *
 * Anchor: TODO: your own image, written by you.
 */
fun viewOrder(capacity: Int, views: List<Charter>): LinkedHashMap<String, Charter> =
    TODO("Not solved yet")
