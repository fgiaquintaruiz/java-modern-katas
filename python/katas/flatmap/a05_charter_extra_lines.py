"""Exercise A05 (Python twin), Charter extra lines.

Statement: Given a list of Charter, produce one formatted line per extra
attached to each charter, using the format ``"<charterId>:<extraName>"``. A
charter with no extras contributes no lines at all.

Concepts: a nested ``for`` clause over an empty list runs zero times, so the
charter contributes nothing without a filter; the inner clause reads the outer
loop variable ``charter`` (the comprehension scope keeps it alive per
iteration); an f-string for the line; why ``map`` plus ``filter`` cannot
express a one-to-many expansion.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from katas.domain import Charter


def extra_lines(charters: list[Charter]) -> list[str]:
    raise NotImplementedError("Not solved yet")
