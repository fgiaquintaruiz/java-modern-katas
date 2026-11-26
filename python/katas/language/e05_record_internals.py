"""Exercise E05 (Python twin), Dataclass internals.

Statement: Complete ``Quote`` with the right decorator options and a
``__post_init__``, then implement ``quote`` to build one. A negative price is
rejected with ``ValueError``, a zero price is accepted, and the route is stored
without surrounding whitespace.

Concepts: ``@dataclass`` generates ``__init__``, ``__eq__`` and ``__repr__``
from the fields in declaration order; ``frozen=True`` adds ``__hash__`` and
makes assignment raise ``FrozenInstanceError``; ``order=True`` generates the
comparison operators over the field tuple; ``__post_init__`` is the compact
constructor, and under ``frozen`` it normalises with ``object.__setattr__``.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from dataclasses import dataclass
from decimal import Decimal


@dataclass
class Quote:
    """Stub: the fields are given, the decorator options and the validation are yours."""

    route: str
    price: Decimal


def quote(route: str, price: Decimal) -> Quote:
    raise NotImplementedError("Not solved yet")
