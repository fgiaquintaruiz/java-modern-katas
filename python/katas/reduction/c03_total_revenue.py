"""Exercise C03 (Python twin), Total Revenue.

Statement: Given a list of Charter, return the sum of their ``total_price`` as
a ``Decimal``. An empty list sums to ``Decimal("0")``.

Concepts: ``functools.reduce(operator.add, prices, Decimal(0))`` as the
two-argument fold with an explicit identity; ``sum(prices, Decimal(0))`` is the
same fold with the start value made explicit because the default ``0`` is an
``int``; ``Decimal`` is immutable, so each step produces a new value and no
accumulator is shared; the identity must be neutral for the fold to be correct
regardless of how the input is split.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from decimal import Decimal

from katas.domain import Charter


def total_revenue(charters: list[Charter]) -> Decimal:
    raise NotImplementedError("Not solved yet")
