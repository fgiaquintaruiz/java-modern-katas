"""Exercise B07 (Python twin), Minimum and maximum daily rate per category, in one pass.

Statement: Given a list of Aircraft, return a dict from each AircraftCategory
present in the list to a RateRange holding the minimum and maximum
``daily_rate`` in that category. Both bounds MUST be computed by visiting each
aircraft exactly once: no second pass over the group, no separate ``min`` and
``max`` calls over the same list.

Concepts: the twin of ``Collectors.teeing`` is one loop that updates two
accumulators side by side, or ``functools.reduce`` with a ``(min, max)`` tuple
as the accumulator; ``min(group)`` plus ``max(group)`` is two passes and is
the thing to avoid here; the frozen dataclass RateRange is the merge step that
turns the two partial results into one value; ``Decimal`` comparison is exact.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from dataclasses import dataclass
from decimal import Decimal

from katas.domain import Aircraft, AircraftCategory


@dataclass(frozen=True)
class RateRange:
    """The minimum and maximum daily rate observed within a category.

    min: lowest ``daily_rate`` in the group
    max: highest ``daily_rate`` in the group
    """

    min: Decimal
    max: Decimal


def rate_range_per_category(aircraft: list[Aircraft]) -> dict[AircraftCategory, RateRange]:
    raise NotImplementedError("Not solved yet")
