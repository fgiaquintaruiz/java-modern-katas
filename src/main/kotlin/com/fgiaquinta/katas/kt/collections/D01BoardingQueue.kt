package com.fgiaquinta.katas.kt.collections

/**
 * Exercise D01 (Kotlin twin) — Boarding queue and last boarded.
 *
 * Statement: Passengers board a charter flight in the order of the given list, first in, first out.
 * Return the last [n] passengers who boarded, most recent first, as a Kotlin [ArrayDeque]. The input
 * list must not be modified.
 *
 * Concepts: kotlin.collections.ArrayDeque, not java.util.ArrayDeque: it is a MutableList, so besides
 * addFirst/addLast/removeFirst/removeLast it supports index access (deque[0]); the same class works as
 * queue and as stack.
 *
 * Internals: TODO: fill after solving.
 *
 * Anchor: TODO: your own image, written by you.
 */
fun lastBoardedFirst(boardingOrder: List<String>, n: Int): ArrayDeque<String> =
    TODO("Not solved yet")
