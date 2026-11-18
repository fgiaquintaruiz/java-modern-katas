# Exercise log: collections D01 BoardingQueue

Source: [D01BoardingQueue.java](../../src/main/java/com/fgiaquinta/katas/collections/D01BoardingQueue.java), test: [D01BoardingQueueTest.java](../../src/test/java/com/fgiaquinta/katas/collections/D01BoardingQueueTest.java).

Format: exercise mode, no clock. Solved on 2026-11-18, first exercise of the collections block. The approach was not stated before coding; noted once, next time the approach comes first.

## Attempts

- Attempt 1: `ArrayDeque` filled with `push` from a stream over the input, an `if`/`else` around the skip, then `queue.stream().toList()`. Green on the first run.
- Detour: after the comparison the learner replaced the deque with `boardingOrder.subList(size - n, size).reversed()`. Reverted on request: the block is about the structure, so the deque stays and the one-liner lives in the javadoc as an alternative.
- Final: single `skip(Math.max(0, size - n))`, `push` per element, `queue.stream().toList()`. The empty-input guard on the first line is redundant but harmless.

## Comparison

- Java 21 `subList(...).reversed()` is a view: zero copies, tied to the source list (topic of D09). The deque version copies twice, once into the ring and once into the result list.
- `push` writes at the head end; iterating a deque from head gives last-in-first-out order without popping anything.

## Internals exchange

- "it extends the AbstractCollection and implements the Deque interface": the type hierarchy is not the storage. Asked for the fields: `Object[] elements`, `int head`, `int tail`.
- On `addFirst`: "you mean the grow part?" The grow is the rare path; the line that matters is `es[head = dec(head, es.length)] = e`.
- On `dec(0, 16)`: "returns zero", then "15 that is the last one?" Yes: the index wraps to the last slot, that is the ring.
- "push rotates the ring": no slot moves, only the head index changes; three pushes on an empty deque go 15, 14, 13.
- "head being the lowest index not empty": head is whatever the field says; after wrap-around it can be higher than tail.
- Draft 2 accepted and transcribed into the javadoc.

## Internals, in the learner's words

We skip `size - n` passengers so we focus on the last passengers of the boarding list. Each push updates the head index, which means one more slot in the ring is occupied. The stream walks the ring from head to tail: if head is index 13 it starts there and continues with 14, 15, 0, 1, 2 and 3 when tail is index 3.

## Anchor, in the learner's words

A toy car with a spring in the rear wheels: when we add a passenger we move the car one click back and charge the spring, and reading the stream is the same as releasing the car.

## Principle question

Asked: `Stack extends Vector` exposes `add(index, e)` and `remove(index)`, so a caller can break "last in on top". Which SOLID letter does that break, and which of the two relationships from the LRU question is `extends`?

- Answer 1: "O because is not extending or adding any feature is just wrapping it". Miss: "wrapping" is has-a, `extends` is is-a.
- Answer 2: "the L of Liskov". Right: a subtype must keep the promises of its type, and `Vector`'s index methods contradict `Stack`'s own promise. Composition over an `ArrayDeque` would hide them.
- Cold re-ask of composition versus inheritance: answered on the second try. Stays on the list.
