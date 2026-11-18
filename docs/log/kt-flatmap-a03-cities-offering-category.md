# Exercise log: Kotlin twin A03 CitiesOfferingCategory

Source: [A03CitiesOfferingCategory.kt](../../src/main/kotlin/com/fgiaquinta/katas/kt/flatmap/A03CitiesOfferingCategory.kt), test: [A03CitiesOfferingCategoryTest.kt](../../src/test/kotlin/com/fgiaquinta/katas/kt/flatmap/A03CitiesOfferingCategoryTest.kt), Java original: [A03CitiesOfferingCategory.java](../../src/main/java/com/fgiaquinta/katas/streams/flatmap/A03CitiesOfferingCategory.java).

Format: Kotlin twin as warm-up, spaced repetition of the Java version solved 2026-10-29. Comparison questions only, no hints counted. Solved 2026-11-17.

## Attempts

- Attempt 1, green: `asSequence().flatMap { airport -> airport.fleet.filter { category.equals(it.category) }.map { airport.city } }.toSet()`. Works, but inside the `flatMap` the eager `List` versions of `filter` and `map` build two lists per airport and push the city once per matching aircraft.
- Comparison question: for an airport with five matching aircraft, how many times does its city reach `toSet`, how many intermediate lists per airport, which function gives `anyMatch` behaviour? Learner: "5 times, 5 lists, any". Five and `any` right; lists are two per airport (one from `filter`, one from `map`), independent of fleet size.
- Attempt 2, green: `asSequence().filter { airport -> airport.fleet.any { category == it.category } }.map { it.city }.toSet()`. The assistant's fragment had used `airport` inside an unnamed lambda; the learner spotted that only `it` exists there and named the parameter.

## Internals, in the learner's words

`toSet` asks for the first airport, then it is filtered by a contained aircraft category matching, then `map` gets the city of that airport and the city name is added to the set. `any` looks at 2 aircraft of 5 if the second one matches and then the pipeline goes back to the filter for the next airport. With two airports in the same city, they meet when the set does the `add` and the one added first survives, because the set does not allow duplicates.

Took three rounds: the first draft skipped what happens to the first airport before the second is pulled; the second said the airport itself is added to the set.

## Anchor, in the learner's words

Browsing a store shelf for a red basketball in boxes that contain more boxes: I put the red ball in my cart, open another big box and find another red ball, look at the cart, I already have one, so I put it away.

## Principle question

Rule changed the same day at the learner's request: Kotlin twins also get one principle question. Asked: `asSequence()` hands back the same method names over the same data but a different type; which pattern, and how does it differ from the B02 Decorator?

- Answer 1: "no idea". Nudge: is `Sequence` the same interface as `List`?
- Answer 2: "decorator". Pointed out that Decorator keeps the interface and `Sequence` is not a `List`.
- Answer 3: "adapter". Right: Adapter changes the interface over the same object, Decorator keeps it and adds behaviour.
- Marked for cold re-ask: Decorator versus Adapter, by interface kept or changed.
