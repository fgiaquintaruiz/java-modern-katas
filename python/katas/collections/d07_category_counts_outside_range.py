"""Exercise D07 (Python twin), Counts for the categories outside a range.

Statement: Given a list of Aircraft and a range of categories
``from_category`` to ``to_category`` (inclusive, in declaration order), return
the aircraft count for every category outside that range as a dict. Every
such category is a key, in enum order, including those with zero aircraft.

Concepts: iterating ``AircraftCategory`` yields the members in declaration
order and a dict keeps insertion order, so a dict filled in that order plays
the role of ``EnumMap``; comparing members with ``<``/``>`` (the enum is
ordered in this domain) or slicing ``list(AircraftCategory)`` is the twin of
``EnumSet.range`` and ``complementOf``; ``dict.fromkeys(categories, 0)``
writes the zeros that a ``Counter`` alone never produces; ``from`` is a
keyword, hence the parameter names.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from katas.domain import Aircraft, AircraftCategory


def count_outside_range(
    fleet: list[Aircraft], from_category: AircraftCategory, to_category: AircraftCategory
) -> dict[AircraftCategory, int]:
    raise NotImplementedError("Not solved yet")
