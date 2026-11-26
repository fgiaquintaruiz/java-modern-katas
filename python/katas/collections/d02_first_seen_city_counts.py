"""Exercise D02 (Python twin), Aircraft per city, first-seen order.

Statement: Given a list of Airport, return a dict from city to the total
number of aircraft in the fleets of the airports in that city. Cities must
iterate in the order they first appear in the list.

Concepts: a plain ``dict`` keeps insertion order since Python 3.7, so it plays
the role of ``LinkedHashMap`` with no extra class; ``d[key] = d.get(key, 0) +
n`` or ``collections.Counter`` with ``update`` is the twin of ``Map.merge``;
re-assigning an existing key keeps its position, deleting and re-inserting
moves it to the end; ``Counter`` is a dict subclass, so it also keeps
first-seen order (``most_common`` reorders only its own output).

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from katas.domain import Airport


def aircraft_count_by_city(airports: list[Airport]) -> dict[str, int]:
    raise NotImplementedError("Not solved yet")
