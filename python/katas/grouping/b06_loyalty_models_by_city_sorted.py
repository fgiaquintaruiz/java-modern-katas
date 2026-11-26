"""Exercise B06 (Python twin), Loyalty members' aircraft models, grouped by city, sorted.

Statement: Given a list of Charter, return a dict from each origin city to the
sorted, distinct aircraft models chartered there by loyalty-member customers.
Keys are in ascending city order and a city whose charters are all by
non-members is absent. There is no SortedMap or SortedSet in the standard
library: a dict whose keys were inserted in sorted order stands in for the
SortedMap (insertion order is guaranteed), and a sorted list of distinct
models stands in for the SortedSet.

Concepts: filtering the source before grouping versus filtering inside the
group, the former never creates the key; ``defaultdict(set)`` to deduplicate
per city, then a dict comprehension over ``sorted(groups)`` that turns each set
into ``sorted(models)``; ``itertools.groupby`` on input sorted by city as the
alternative; ``sorted`` is a full barrier that sees every element first.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from katas.domain import Charter


def loyalty_models_by_city(charters: list[Charter]) -> dict[str, list[str]]:
    raise NotImplementedError("Not solved yet")
