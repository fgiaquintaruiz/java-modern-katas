"""Exercise M07 (Python twin), Sorted unique registrations.

Statement: Return the distinct registrations in natural order. Python has no
``TreeSet``, so a sorted ``list`` plays its role. Passing the ``set`` class as
a callable to build the distinct collection is REQUIRED; a lambda is not
accepted.

Concepts: a class is a callable value, the twin of ``TreeSet::new`` as a
``Supplier``; ``set`` collapses duplicates by hash and forgets order,
``sorted`` restores natural order in one pass; ``dict.fromkeys`` keeps
first-seen order instead, when that is what is wanted; strings compare by
code point, so ``"EC-AAA" < "EC-BBB"``.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""


def sorted_unique_registrations(registrations: list[str]) -> list[str]:
    raise NotImplementedError("Not solved yet")
