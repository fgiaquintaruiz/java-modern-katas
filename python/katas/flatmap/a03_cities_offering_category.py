"""Exercise A03 (Python twin), Cities offering a category.

Statement: Given a list of Airport and an AircraftCategory, return the set of
city names of airports that have at least one aircraft of that category in their
fleet. Two airports sharing the same city collapse into a single entry, since
city identity is what matters here, not airport identity.

Concepts: ``any`` over a generator expression on the inner fleet as a
short-circuiting predicate, it stops at the first aircraft that matches; a set
comprehension collapses duplicate cities through hash and equality; no
flattening here, because the city lives on the airport, not on the aircraft.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from katas.domain import Airport, AircraftCategory


def cities_offering(airports: list[Airport], category: AircraftCategory) -> set[str]:
    raise NotImplementedError("Not solved yet")
