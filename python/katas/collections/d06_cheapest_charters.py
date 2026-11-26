"""Exercise D06 (Python twin), The k cheapest charters.

Statement: Return the ``k`` charters with the lowest total price, from
cheapest to most expensive. If there are fewer than ``k`` charters, all of
them are returned.

Concepts: Python's priority queue is a plain list managed by ``heapq``
(``heappush``/``heappop`` O(log n), ``heap[0]`` O(1) peek);
``heapq.nsmallest(k, charters, key=...)`` plays the role of ``PriorityQueue``
plus ``k`` polls and already hands back the ``k`` smallest in ascending order;
``Charter`` is not orderable, so a raw heap of charters would need
``(price, counter, charter)`` tuples, the twin of ``Comparator.comparing``;
iterating the heap list is not sorted, popping it is.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from katas.domain import Charter


def cheapest(charters: list[Charter], k: int) -> list[Charter]:
    raise NotImplementedError("Not solved yet")
