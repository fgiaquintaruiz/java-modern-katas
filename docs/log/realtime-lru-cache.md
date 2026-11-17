# Exercise log: realtime LruCache

Source: [LruCache.java](../../src/main/java/com/fgiaquinta/katas/realtime/LruCache.java), test: [LruCacheTest.java](../../src/test/java/com/fgiaquinta/katas/realtime/LruCacheTest.java), format: [package-info](../../src/main/java/com/fgiaquinta/katas/realtime/package-info.java).

Format: timed exercise, 25-minute box, approach stated before coding, hints counted. Solved on 2026-11-12, finished off the clock, tests and internals closed 2026-11-17.

## Timed run (13:18 to 13:44, 0 hints)

- Attempt 1: `HashMap<K, V>` for storage plus a `TreeMap<Integer, K>` meant to track recency. The TreeMap was never initialised and orders by key, not by use, so recency was not represented.
- Attempt 2: switched to `LinkedHashMap` with the two-argument constructor. `put` threw an exception when the capacity was reached instead of evicting. `get` had an empty `if`.
- Clock stopped with a partial submission. Feedback: structure choice was the blocker; the candidate knew a map was needed but not which map keeps recency.

## Off the clock

- Assistant explained, as a library gap: the doubly linked list inside `LinkedHashMap`, the three-argument constructor with `accessOrder = true`, and the `removeEldestEntry` hook with its shape. The eviction condition was left to the learner.
- Learner wrote the final version: anonymous subclass, `size() > capacity` as the condition, `get` and `put` delegating to the map.
- Tests: learner wrote three (capacity two eviction, a read protects a key, update does not evict). First version asserted only the evicted key; second version added the survivors. All green.

## Internals, in the learner's words

`get("key1")` moves the entry to the tail of the list; the hash bucket stays put, only the list links change. `put("key3")` inserts and appends at the tail, then the hook receives the head (`key2`); `size()` is already three against a capacity of two, so it returns true and the map removes it.

## Anchor, in the learner's words

A queue at the bank with no numbers: each person only knows who was last when they arrived, and a clerk comes to one person and says "now you go after the person with the blue jacket".

## Principle question

Asked: which pattern is `removeEldestEntry`, and how does it differ from the `Collector` handed to `collect` in B01?

- Answer 1: "template method, different because it only works if you override it". Pattern right, contrast unclear.
- Answer 2: "polymorphism vs inheritance, the first can change at runtime". Runtime split right; "polymorphism" does not separate the two since the override is also a virtual call.
- Answer 3: "no idea". Explained after the third miss: composition (has-a, Strategy, swappable per call) versus inheritance (is-a, Template Method, fixed when the class is written).
- Marked for cold re-ask: composition versus inheritance.
