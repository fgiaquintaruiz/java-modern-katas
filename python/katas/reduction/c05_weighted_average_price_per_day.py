"""Exercise C05 (Python twin), Weighted Average Price Per Day.

Statement: Given a list of Charter, compute the sum of all ``total_price``
divided by the sum of all ``days()``, quantized to two decimal places with
``ROUND_HALF_UP``. An empty list returns ``Decimal("0.00")``. The computation
MUST use ``functools.reduce`` with the frozen dataclass Totals(price, days)
nested in this module as the accumulator, in a single pass, with
``Totals(Decimal(0), 0)`` as the identity.

Concepts: a fold whose accumulator type differs from the element type, so the
step function is ``(Totals, Charter) -> Totals``; the accumulator is immutable,
each step allocates a new Totals, the price of purity versus a mutable running
pair; ``Decimal.quantize(Decimal("0.01"), rounding=ROUND_HALF_UP)`` fixes both
scale and rounding mode, ``round()`` would not; a combiner merging two Totals
must be associative for the fold to split correctly.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from dataclasses import dataclass
from decimal import Decimal

from katas.domain import Charter


@dataclass(frozen=True)
class Totals:
    """Immutable partial accumulator used by the fold to track the running
    total price and the running total number of days in a single pass.

    price: sum of ``total_price`` seen so far
    days: sum of ``days()`` seen so far
    """

    price: Decimal
    days: int


def weighted_average_price_per_day(charters: list[Charter]) -> Decimal:
    raise NotImplementedError("Not solved yet")
