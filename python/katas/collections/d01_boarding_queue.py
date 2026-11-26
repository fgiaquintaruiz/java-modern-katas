"""Exercise D01 (Python twin), Boarding queue and last boarded.

Statement: Passengers board a charter flight in the order of the given list,
first in, first out. Return the last ``n`` passengers who boarded, most recent
first, as a ``collections.deque``. The input list must not be modified.

Concepts: ``collections.deque`` plays the role of ``ArrayDeque``: ``append``
plus ``popleft`` make it a queue, ``appendleft`` plus ``popleft`` (or
``append`` plus ``pop``) make it a stack, same object, different end;
``deque(iterable, maxlen=n)`` drops from the opposite end when full, so a
bounded deque fed in boarding order keeps exactly the last ``n``; ``reversed``
or ``appendleft`` turns boarding order into most-recent-first; a deque is a
doubly linked list of fixed-size blocks, O(1) at both ends, O(n) in the middle.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from collections import deque


def last_boarded_first(boarding_order: list[str], n: int) -> deque[str]:
    raise NotImplementedError("Not solved yet")
