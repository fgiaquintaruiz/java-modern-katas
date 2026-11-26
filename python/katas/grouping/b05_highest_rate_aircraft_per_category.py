"""Exercise B05 (Python twin), Highest daily rate aircraft per category.

Statement: Given a list of Aircraft, return a dict from each AircraftCategory
present in the list to the aircraft with the highest ``daily_rate`` within that
category. On a tie the first aircraft in encounter order wins.

Concepts: ``max(group, key=...)`` as the per-key downstream, it keeps the first
maximal element on ties; a group built by ``defaultdict(list)`` then reduced,
versus a single pass that keeps only the current best per key; no Optional is
needed because a key only exists once it has at least one aircraft; ``None`` is
the Python stand-in should the group ever be empty.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from katas.domain import Aircraft, AircraftCategory


def highest_rate_per_category(aircraft: list[Aircraft]) -> dict[AircraftCategory, Aircraft]:
    raise NotImplementedError("Not solved yet")
