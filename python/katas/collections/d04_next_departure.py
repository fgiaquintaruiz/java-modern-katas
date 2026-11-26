"""Exercise D04 (Python twin), Departure neighbours.

Statement: Given a departure schedule (departure time to charter, in no
particular order) and a time, return the first charter departing at or after
that time and the last charter departing strictly before it, as a Neighbours.
Either side is ``None`` when there is none.

Concepts: Python has no ``TreeMap``; a sorted list of keys plus ``bisect``
plays its role: ``bisect_left(keys, at)`` is the index of the ceiling (at or
after) and the index just before it is the lower (strictly before), while
``bisect_right`` would make the previous side at-or-before; ``sorted(schedule)``
sorts the keys and ``datetime.time`` orders naturally; the sorted list costs
O(n log n) once, each lookup O(log n); a frozen dataclass with two optional
fields is the twin of a record of two Optionals.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from dataclasses import dataclass
from datetime import time

from katas.domain import Charter


@dataclass(frozen=True)
class Neighbours:
    """The departures around a point in time.

    previous: last charter departing strictly before the time, or None
    next: first charter departing at or after the time, or None
    """

    previous: Charter | None
    next: Charter | None


def neighbours(schedule: dict[time, Charter], at: time) -> Neighbours:
    raise NotImplementedError("Not solved yet")
