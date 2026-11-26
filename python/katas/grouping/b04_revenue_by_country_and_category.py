"""Exercise B04 (Python twin), Revenue grouped by country, then by aircraft category.

Statement: Given a list of Charter, return a dict from the origin airport's
country to a nested dict from AircraftCategory to the total ``total_price`` of
charters that picked up an aircraft of that category in that country.

Concepts: a two-level ``defaultdict(lambda: defaultdict(Decimal))`` as the twin
of nested ``groupingBy``; ``Decimal()`` is zero and ``+=`` on it is the summing
downstream; one inner dict allocated per country plus the outer one; a plain
dict comprehension cannot express the nesting in one pass without a second
grouping step; ``Decimal`` keeps the scale, ``sum`` of floats would not.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from decimal import Decimal

from katas.domain import AircraftCategory, Charter


def revenue_by_country_and_category(
    charters: list[Charter],
) -> dict[str, dict[AircraftCategory, Decimal]]:
    raise NotImplementedError("Not solved yet")
