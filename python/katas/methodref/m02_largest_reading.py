"""Exercise M02 (Python twin), Largest reading.

Statement: Return the largest flight-hours reading, or ``None`` when there are
none. The two-argument builtin ``max`` passed as the reducer to
``functools.reduce`` is REQUIRED; a lambda is not accepted.

Concepts: ``functools.reduce`` folds from the left with a two-argument
function, the twin of a ``BinaryOperator``; ``reduce`` over an empty iterable
raises ``TypeError`` unless an initial value is given, so the empty case is
decided before the fold; ``None`` as the stand-in for an empty Optional;
``max(iterable, default=None)`` is the one-liner that hides the same fold.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""


def largest_reading(flight_hour_readings: list[int]) -> int | None:
    raise NotImplementedError("Not solved yet")
