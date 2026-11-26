"""Exercise E07 (Python twin), Closures that rebind and lambdas in a loop.

Statement: ``count_long_charters`` counts the charters lasting at least
``min_days`` days with a nested function ``tally(charter)`` that increments a
local counter of the enclosing function, called once per charter. Without
``nonlocal`` the increment raises ``UnboundLocalError``. ``id_suppliers``
returns one zero-argument callable per charter, each giving that charter's id,
built inside a loop.

Concepts: assignment inside a nested function makes the name local, so
``counter += 1`` needs ``nonlocal`` to reach the enclosing cell (Java forbids
the rebinding; Python allows it and asks you to say so); a lambda created in a
loop looks up the loop variable when called, not when created, so every lambda
sees the last value unless the value is bound early (default argument,
``functools.partial`` or ``operator.attrgetter``).

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from collections.abc import Callable

from katas.domain import Charter


def count_long_charters(charters: list[Charter], min_days: int) -> int:
    raise NotImplementedError("Not solved yet")


def id_suppliers(charters: list[Charter]) -> list[Callable[[], str]]:
    raise NotImplementedError("Not solved yet")
