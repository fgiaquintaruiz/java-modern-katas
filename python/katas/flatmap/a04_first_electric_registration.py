"""Exercise A04 (Python twin), First electric registration.

Statement: Given a list of Airport, scan airports and their fleets in order for
the first aircraft whose engine type is ELECTRIC, and return its registration,
or ``None`` when there is none. The scan must stop logically at the first
match; nothing after it needs to be inspected for the result to be correct.

Concepts: ``next(generator, None)`` as the short-circuiting terminal; a
generator expression with nested ``for`` clauses stays lazy, so the aircraft
after the match is never read; ``None`` as the Python stand-in for an empty
Optional; a list comprehension in the same place would read every fleet first.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from katas.domain import Airport


def first_electric_registration(airports: list[Airport]) -> str | None:
    raise NotImplementedError("Not solved yet")
