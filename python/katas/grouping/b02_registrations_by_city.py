"""Exercise B02 (Python twin), Registrations grouped by city.

Statement: Given a list of Airport, return a dict from each city with at least
one aircraft to the list of registrations of all aircraft parked in airports of
that city. Airports that share the same city merge their registrations into
that city's single list.

Concepts: ``defaultdict(list)`` creating the per-key list lazily on first
access, the twin of ``computeIfAbsent``; a nested ``for`` over airports then
fleet appends in encounter order, so order survives inside each group; an
airport with an empty fleet never touches the dict, so no empty bucket is
created; ``itertools.groupby`` is not a fit here because the input is not
sorted by city.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from katas.domain import Airport


def registrations_by_city(airports: list[Airport]) -> dict[str, list[str]]:
    raise NotImplementedError("Not solved yet")
