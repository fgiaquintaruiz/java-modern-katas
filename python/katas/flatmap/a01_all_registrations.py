"""Exercise A01 (Python twin), All registrations.

Statement: Given a list of Airport, flatten the fleets of all airports into a
single list of registrations. This is the canonical one-to-many flattening
exercise: airports map to fleets, and fleets map to registrations.

Concepts: a comprehension with two ``for`` clauses as one-to-many flattening;
``itertools.chain.from_iterable`` as the lazy alternative; a list comprehension
is eager and allocates the whole result, a generator expression hands out one
element at a time; encounter order survives flattening.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from katas.domain import Airport


def all_registrations(airports: list[Airport]) -> list[str]:
    raise NotImplementedError("Not solved yet")
