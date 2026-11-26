"""Exercise D05 (Python twin), Registrations ordered by length, then alphabetically.

Statement: Return the registrations of the given aircraft as a list ordered by
length first and then alphabetically ignoring case. Two registrations that
differ only in case are the same element, and the first one seen is the one
kept.

Concepts: Python has no ``TreeSet``; a sorted list plays its role, and a dict
keyed by the normalised key ``(len(r), r.casefold())`` filled with
``setdefault`` collapses the duplicates while keeping the first inserted, the
twin of a comparator deciding equality instead of ``equals``; ``sorted`` with
a tuple key compares lexicographically, length first then text; ``bisect`` on
the keys gives ``higher``/``lower``, list indexes give ``first``/``last``.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from katas.domain import Aircraft


def ordered_registrations(fleet: list[Aircraft]) -> list[str]:
    raise NotImplementedError("Not solved yet")
