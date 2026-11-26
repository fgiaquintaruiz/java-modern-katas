"""Exercise A02 (Python twin), Distinct features sorted.

Statement: Given a list of Airport, collect the distinct feature tags of every
aircraft across all airports, sorted alphabetically. Two levels of nesting must
be flattened: airports into aircraft, and aircraft into feature tags.

Concepts: three ``for`` clauses in one comprehension (airports to aircraft to
features); ``set`` as the deduplication step, hashing each tag once; ``sorted``
as a full barrier that must see every element before it emits the first one;
``sorted(set(...))`` versus ``sorted(dict.fromkeys(...))`` when insertion order
has to survive the deduplication.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from katas.domain import Airport


def distinct_features_sorted(airports: list[Airport]) -> list[str]:
    raise NotImplementedError("Not solved yet")
