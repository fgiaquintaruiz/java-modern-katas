"""Exercise D03 (Python twin), Recently viewed charters.

Statement: A screen remembers the last ``capacity`` distinct charters a user
viewed. Given the sequence of views, return the charter ids from least to
most recently viewed. Viewing a charter again makes it the most recent; when
the capacity is exceeded the least recently viewed one is dropped.

Concepts: ``collections.OrderedDict`` plays the role of ``LinkedHashMap`` with
``accessOrder = true``: ``move_to_end(key)`` on a repeated view and
``popitem(last=False)`` to evict the oldest, the twin of ``removeEldestEntry``;
a plain dict does the same with ``del`` and re-insert; neither dict reorders
on a read, so the "a get counts as an access" rule has to be written by hand;
``functools.lru_cache`` is this very structure behind a decorator.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from katas.domain import Charter


def view_order(capacity: int, views: list[Charter]) -> list[str]:
    raise NotImplementedError("Not solved yet")
