"""Exercise C01 (Python twin), Total Flight Hours.

Statement: Given a list of Aircraft, return the sum of their ``flight_hours``
values as a single ``int``. An empty list sums to ``0``.

Concepts: ``sum(generator)`` as the specialised fold, with ``0`` as its implicit
start value; a generator expression hands one value at a time, a list
comprehension would allocate the whole intermediate list first; Python ``int``
is unbounded, so there is no boxing and no overflow, which is the whole point
of ``mapToLong`` in Java and is simply absent here; ``functools.reduce`` with
``operator.add`` as the explicit, more general form.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from katas.domain import Aircraft


def total_flight_hours(aircraft: list[Aircraft]) -> int:
    raise NotImplementedError("Not solved yet")
