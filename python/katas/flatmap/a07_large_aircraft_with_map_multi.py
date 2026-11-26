"""Exercise A07 (Python twin), Large aircraft with a push-style generator.

Statement: Given a list of Airport and a minimum seat count, return every
aircraft across all airports whose ``seats`` is greater than or equal to
``min_seats``. Java solves this with ``mapMulti``; the Python twin MUST be built
on a generator function that ``yield``s each matching aircraft downstream, with
``list(...)`` as the only collection built. A nested comprehension that
materialises an inner list per airport is NOT accepted, even though it
produces the same result.

Concepts: ``yield`` as the Python shape of ``mapMulti``, the function pushes
elements to the consumer instead of returning an inner collection; no
per-airport allocation, the generator frame is the only state; compare with
``itertools.chain.from_iterable`` plus ``filter``, which also stays lazy but
builds the pipeline from two generic pieces instead of one explicit loop.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from katas.domain import Aircraft, Airport


def large_aircraft(airports: list[Airport], min_seats: int) -> list[Aircraft]:
    raise NotImplementedError("Not solved yet")
