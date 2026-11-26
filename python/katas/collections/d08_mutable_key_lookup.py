"""Exercise D08 (Python twin), Lookup after a key mutates.

Statement: Register a MutableRoute in a set, then rename the route to
``new_code``. Return whether the registry still finds the route after the
rename. The route must end up renamed, and the answer must be ``True``.

Concepts: a ``set`` or ``dict`` places an entry by the hash computed at
insertion time and a later lookup probes with the new hash, so mutating a
field used by ``__hash__`` strands the entry; a non-frozen ``@dataclass`` with
``eq=True`` sets ``__hash__`` to ``None`` on purpose, and ``unsafe_hash=True``
is the switch that re-enables the trap; ``frozen=True`` is the defensive key,
the twin of a record; the order of rename and insertion is the whole exercise.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from dataclasses import dataclass


@dataclass(unsafe_hash=True)
class MutableRoute:
    """Mutable on purpose: ``__eq__`` and ``__hash__`` depend on a field that
    can be reassigned, which is exactly what ``unsafe_hash`` warns about."""

    code: str


def contains_after_rename(route: MutableRoute, new_code: str) -> bool:
    raise NotImplementedError("Not solved yet")
