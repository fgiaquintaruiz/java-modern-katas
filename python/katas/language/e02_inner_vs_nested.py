"""Exercise E02 (Python twin), Closure versus plain nested function.

Statement: Given a function, return a ``Shape`` saying whether it holds a hidden
reference to an enclosing scope, and the names of the variables it captures.
Use ``FleetOffice.nested()`` and ``FleetOffice.inner()`` to see the two cases:
the first returns a function that captures nothing, the second a closure that
reads ``self``.

Concepts: Python has no inner classes with an outer pointer; the counterpart of
Java's synthetic ``this$0`` is a closure cell; ``fn.__code__.co_freevars`` lists
the captured names, ``fn.__closure__`` holds the cells (``None`` when nothing is
captured) and ``cell.cell_contents`` is the captured object; a closure over
``self`` keeps the whole instance alive, the same leak an inner class causes.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from collections.abc import Callable
from dataclasses import dataclass


class FleetOffice:
    def __init__(self) -> None:
        self.airline = "Charter Air"

    def nested(self) -> Callable[[], str]:
        """A plain nested function: it reads nothing from the enclosing scope."""

        def name() -> str:
            return "nested"

        return name

    def inner(self) -> Callable[[], str]:
        """A closure: it reads ``self`` from the enclosing scope, so it captures it."""

        def airline() -> str:
            return self.airline

        return airline


@dataclass(frozen=True)
class Shape:
    has_outer_reference: bool
    free_variables: tuple[str, ...]


def inspect(fn: Callable[..., object]) -> Shape:
    raise NotImplementedError("Not solved yet")
