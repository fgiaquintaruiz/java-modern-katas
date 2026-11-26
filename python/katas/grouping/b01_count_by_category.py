"""Exercise B01 (Python twin), Count aircraft by category.

Statement: Given a list of Aircraft, return a dict from each AircraftCategory
present in the list to the count of aircraft in that category. Categories with
no aircraft are absent from the result.

Concepts: ``collections.Counter`` fed a generator of keys as the twin of
``groupingBy`` with ``counting()``; ``defaultdict(int)`` plus ``+= 1`` as the
explicit accumulator; a ``Counter`` is a dict subclass, so equality against a
plain dict holds; the count is an unbounded ``int``, there is no Long versus
Integer split.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from katas.domain import Aircraft, AircraftCategory


def count_by_category(aircraft: list[Aircraft]) -> dict[AircraftCategory, int]:
    raise NotImplementedError("Not solved yet")
