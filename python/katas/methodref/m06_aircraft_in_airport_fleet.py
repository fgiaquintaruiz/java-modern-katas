"""Exercise M06 (Python twin), Aircraft in airport fleet.

Statement: Keep only the candidates that belong to the airport fleet, in input
order. The bound method ``airport.fleet.__contains__`` as the predicate is
REQUIRED; a lambda is not accepted.

Concepts: the receiver of a bound method can be any expression result, the
twin of ``airport.fleet()::contains``; the method is bound once, when the
expression is evaluated, not on every call; ``list.__contains__`` walks the
list and compares with ``==``, so dataclass equality by value decides
membership, O(n) per candidate.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from katas.domain import Aircraft, Airport


def in_fleet(candidates: list[Aircraft], airport: Airport) -> list[Aircraft]:
    raise NotImplementedError("Not solved yet")
