"""Exercise M08 (Python twin), Flat priced extras.

Statement: Build one ``Extra`` per name, all with the given price, and return
them as a list in input order (a list plays the role of the Java array).
``functools.partial(Extra, price=price)`` as the one-argument factory passed
to ``map`` is REQUIRED; a lambda is not accepted.

Concepts: a dataclass is a callable that builds instances, the twin of
``Extra::new``; ``functools.partial`` freezes some arguments and returns a new
callable, the twin of adapting a two-argument constructor into a one-argument
function; ``list`` collecting the mapped values is the twin of
``toArray(Extra[]::new)``; Python has no typed object arrays, ``array.array``
holds only numbers.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from decimal import Decimal

from katas.domain import Extra


def flat_priced_extras(names: list[str], price: Decimal) -> list[Extra]:
    raise NotImplementedError("Not solved yet")
