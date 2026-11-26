"""Exercise C06 (Python twin), Top 3 Aircraft By Revenue.

Statement: Given a list of Charter, group them by aircraft registration, sum
the ``total_price`` of each aircraft's charters, and return the registrations
of the three aircraft with the highest summed revenue, ordered from highest to
lowest. Ties are broken by registration ascending.

Concepts: ``defaultdict(Decimal)`` with ``+=`` as the grouping plus summing
step; ``sorted(totals.items(), key=lambda kv: (-kv[1], kv[0]))`` encodes
descending revenue then ascending registration in one key tuple; slicing
``[:3]`` after ``sorted`` means the whole list is sorted first, whereas
``heapq.nlargest(3, ...)`` keeps only three; ``sorted`` is stable, which is
why the tie-break can also be done in two sorts.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from katas.domain import Charter


def top3_registrations_by_revenue(charters: list[Charter]) -> list[str]:
    raise NotImplementedError("Not solved yet")
