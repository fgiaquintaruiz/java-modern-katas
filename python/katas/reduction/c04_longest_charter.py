"""Exercise C04 (Python twin), Longest Charter.

Statement: Given a list of Charter, return the one with the greatest
``days()``, or ``None`` when the list is empty. On a tie the first charter in
encounter order wins.

Concepts: ``max(charters, key=Charter.days, default=None)`` as the reduction;
``key`` receives each element once, the twin of ``comparingLong``; ``max``
keeps the first maximal element on ties, same as ``maxBy``; ``default`` is
what makes the empty case return ``None`` instead of raising ``ValueError``,
and ``None`` is the stand-in for an empty Optional.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from katas.domain import Charter


def longest_charter(charters: list[Charter]) -> Charter | None:
    raise NotImplementedError("Not solved yet")
