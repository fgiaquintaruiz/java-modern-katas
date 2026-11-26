"""Exercise B03 (Python twin), Average price of long vs. short charters.

Statement: Given a list of Charter and a minimum number of days ``min_days``,
split charters into "long" (key ``True``, ``days() >= min_days``) and "short"
(key ``False``) groups. Return a dict from bool to the average ``total_price``
of each group, as a ``float``. Both keys are always present; an empty group
averages to ``0.0``.

Concepts: a partition is a grouping whose key space is fixed to ``{True,
False}``, so both buckets must be created up front, unlike a ``defaultdict``
that only materialises keys it sees; ``statistics.mean`` raises on an empty
sequence, so the empty case needs an explicit guard; ``float(Decimal)`` loses
precision, which is the price of returning a ``float``.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from katas.domain import Charter


def average_price_by_long_charter(charters: list[Charter], min_days: int) -> dict[bool, float]:
    raise NotImplementedError("Not solved yet")
