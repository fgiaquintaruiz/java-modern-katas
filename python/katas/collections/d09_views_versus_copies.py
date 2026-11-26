"""Exercise D09 (Python twin), Views versus copies.

Statement: Given a mutable list of registrations (at least two elements),
build five objects from it and return them in a Views: a live iterator over
the list, an independent immutable snapshot, a slice of its first two
elements, a byte buffer holding the registrations joined by commas, and a
memoryview over that buffer. Which ones follow later changes of the source,
which accept writes and which can grow is what the tests check.

Concepts: Python has no read-only list wrapper and no write-through sublist;
``iter(source)`` is the only standard view of a list, it follows the source
until exhausted and has no fail-fast check; ``tuple(source)`` is the snapshot,
the twin of ``List.copyOf``; ``source[:2]`` COPIES, unlike ``subList``, so
writes to it never reach the source; ``memoryview(bytearray)`` is the genuine
write-through view, the twin of ``Arrays.asList`` over its array: writes reach
the buffer in both directions and the buffer cannot be resized while the view
exists (``BufferError``).

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from collections.abc import Iterator
from dataclasses import dataclass


@dataclass(frozen=True)
class Views:
    """Five objects built from one source list, each with its own relation to it.

    live_iterator: iterator over the source list itself, follows later changes
    snapshot: immutable copy taken at build time
    first_two: slice of the first two elements, a copy, not a view
    backing_buffer: bytes of the registrations joined by commas, ASCII encoded
    buffer_view: memoryview over backing_buffer, write-through in both directions
    """

    live_iterator: Iterator[str]
    snapshot: tuple[str, ...]
    first_two: list[str]
    backing_buffer: bytearray
    buffer_view: memoryview


def build(source: list[str]) -> Views:
    raise NotImplementedError("Not solved yet")
