"""Exercise E06 (Python twin), Closed union and structural pattern matching.

Statement: Describe a ``Status`` with a ``match`` statement over class patterns
and a final ``case _`` that calls ``typing.assert_never``. Scheduled gives
``"Scheduled R1"`` (the charter id), Delayed gives ``"Delayed 15 min"``, or
``"Heavily delayed 90 min"`` from 60 minutes up, and Cancelled gives
``"Cancelled: weather"``. Anything else reaches the fallback and raises.

Concepts: a ``Union`` of frozen dataclasses is Python's sealed hierarchy, closed
by the type alias rather than by the compiler; class patterns deconstruct by
``__match_args__``, which ``@dataclass`` generates from the fields; a guard
``if`` on a case; exhaustiveness is checked statically by ``assert_never`` and
at runtime by the fallback raising ``AssertionError``.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from dataclasses import dataclass

from katas.domain import Charter


@dataclass(frozen=True)
class Scheduled:
    charter: Charter


@dataclass(frozen=True)
class Delayed:
    minutes: int


@dataclass(frozen=True)
class Cancelled:
    reason: str


Status = Scheduled | Delayed | Cancelled


def describe(status: Status) -> str:
    raise NotImplementedError("Not solved yet")
