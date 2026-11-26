"""Exercise C02 (Python twin), Charter Duration Stats.

Statement: Given a list of Charter, compute summary statistics (count, sum,
min, average, max) of ``days()`` across all charters in a single pass, returned
as a LongSummaryStatistics. There is no ``java.util.LongSummaryStatistics`` in
Python, so the frozen dataclass next to the function carries the same five
fields. On empty input it reproduces the JDK sentinels: count ``0``, sum ``0``,
min ``LONG_MAX``, max ``LONG_MIN``, average ``0.0``.

Concepts: a single loop that updates five running values is the mutable
reduction; ``min``, ``max``, ``sum`` and ``statistics.mean`` each walk the data
again, so four of them are four passes; ``functools.reduce`` with a tuple
accumulator as the immutable single-pass form; the sentinel pair
``(LONG_MAX, LONG_MIN)`` is the identity for min and max, which is why an empty
result does not read ``0``.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from dataclasses import dataclass

from katas.domain import Charter

LONG_MAX = 2**63 - 1
LONG_MIN = -(2**63)


@dataclass(frozen=True)
class LongSummaryStatistics:
    """Twin of ``java.util.LongSummaryStatistics``, same five fields.

    count: number of values seen
    sum: total of all values
    min: smallest value, ``LONG_MAX`` when no value was seen
    max: largest value, ``LONG_MIN`` when no value was seen
    average: ``sum / count`` as a float, ``0.0`` when no value was seen
    """

    count: int
    sum: int
    min: int
    max: int
    average: float


def duration_stats(charters: list[Charter]) -> LongSummaryStatistics:
    raise NotImplementedError("Not solved yet")
