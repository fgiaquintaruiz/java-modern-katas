"""Exercise M04 (Python twin), Aircraft by daily rate.

Statement: Return the aircraft ordered by ascending daily rate, without
modifying the input. ``operator.attrgetter("daily_rate")`` as the ``key`` of
``sorted`` is REQUIRED; a lambda is not accepted.

Concepts: ``sorted`` returns a new list and leaves the input alone, while
``list.sort`` works in place; ``key=`` is the twin of ``Comparator.comparing``:
one key extraction per element, then the keys are compared, never the
elements; ``Decimal`` keys compare by numeric value; Timsort is stable, so
equal rates keep their input order.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from katas.domain import Aircraft


def sorted_by_daily_rate(aircraft: list[Aircraft]) -> list[Aircraft]:
    raise NotImplementedError("Not solved yet")
