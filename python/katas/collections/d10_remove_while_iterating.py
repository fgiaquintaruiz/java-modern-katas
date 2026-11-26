"""Exercise D10 (Python twin), Remove while iterating.

Statement: Remove from the given mutable list every charter lasting fewer than
``min_days`` days, in place, and return the same list instance. Charters
lasting exactly ``min_days`` stay, and the order of the survivors does not
change.

Concepts: a list iterator walks by index, so removing the current element
inside a ``for`` silently skips the next one instead of raising, Python's
quieter version of ``ConcurrentModificationException``; dicts and sets do
raise ``RuntimeError`` when their size changes during iteration; the in-place
options are slice assignment ``lst[:] = [c for c in lst if ...]``, iterating
over a copy ``for c in list(lst)``, or walking backwards over the indexes; a
comprehension alone returns a new list and fails the same-instance rule.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from katas.domain import Charter


def remove_shorter_than(charters: list[Charter], min_days: int) -> list[Charter]:
    raise NotImplementedError("Not solved yet")
